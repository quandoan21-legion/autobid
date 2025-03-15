package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.bids;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.BidsRepo;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BidService {
    @Autowired
    private BidsRepo bidsRepo;

    @Autowired
    private UserInformationRepo userInformationRepo;

    @Autowired
    private UserInformationService userInformationService;

    private final MessageFactory message = new MessageFactory();

    @Transactional
    public MessageFactory placeBid(Integer carId, Integer userId, double bidAmount) {
        // Find the current highest bid for the auction
        Optional<bids> highestBidOpt = bidsRepo.findHighestBidByAuctionId(carId);

        // Deduct the bid amount from the new bidder's balance
        users newBidder = userInformationRepo.findById(userId).orElse(null);
        if (newBidder == null) {
            return message.MessageResponse("Invalid user ID", false, List.of());
        }

        if (newBidder.getBalance() < bidAmount) {
            return message.MessageResponse("Insufficient balance", false, List.of());
        }

        newBidder.deductBalance(bidAmount);
        userInformationRepo.save(newBidder);

        if (highestBidOpt.isPresent()) {
            bids highestBid = highestBidOpt.get();

            // Check if the new bid is higher than the current highest bid
            if (bidAmount > highestBid.getBid_amount()) {
                // Refund the previous highest bidder
                refundBidder(highestBid);

                // Save the new bid
                bids newBid = new bids();
                newBid.setUser_id(userId);
                newBid.setAuction_id(carId);
                newBid.setBid_amount(bidAmount);
                newBid.setBid_time(new Date());
                bidsRepo.save(newBid);

                return message.MessageResponse("Bid placed successfully", true, List.of());
            } else {
                // If the new bid is not higher, refund the new bidder
                newBidder.setBalance(newBidder.getBalance() + bidAmount);
                userInformationRepo.save(newBidder);

                return message.MessageResponse("New bid must be higher than the current highest bid", false, List.of());
            }
        } else {
            // If no bids exist, save the new bid
            bids newBid = new bids();
            newBid.setUser_id(userId);
            newBid.setAuction_id(carId);
            newBid.setBid_amount(bidAmount);
            newBid.setBid_time(new Date());
            bidsRepo.save(newBid);

            return message.MessageResponse("Bid placed successfully", true, List.of());
        }
    }

    private void refundBidder(bids bid) {
        // Refund the previous highest bidder
        users previousBidder = userInformationRepo.findById(bid.getUser_id()).orElse(null);
        if (previousBidder != null) {
            previousBidder.setBalance(previousBidder.getBalance() + bid.getBid_amount());
            userInformationRepo.save(previousBidder);
        }
    }
}
