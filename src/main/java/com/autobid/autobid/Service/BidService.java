package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.bids;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.BidsRepo;
import com.autobid.autobid.Repository.CarInformationRepo;
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
    private CarInformationRepo carInformationRepo; // Add this repository

    private final MessageFactory message = new MessageFactory();

    @Transactional
    public MessageFactory placeBid(Integer carId, Integer userId, double bidAmount) {
        // Fetch the car information
        car_information car = carInformationRepo.findById(carId).orElse(null);
        if (car == null) {
            return message.MessageResponse("Invalid car ID", false, List.of());
        }

        // Check if the bid is lower than the starting bid
        if (bidAmount < car.getStarting_bid()) {
            return message.MessageResponse("Bid amount must be greater than or equal to the starting bid", false, List.of());
        }

        // Find the current highest bid for the auction
        Optional<bids> highestBidOpt = bidsRepo.findHighestBidByAuctionId(carId);

        // Check if the new bid is higher than the current highest bid
        if (highestBidOpt.isPresent() && bidAmount <= highestBidOpt.get().getBid_amount()) {
            return message.MessageResponse("New bid must be higher than the current highest bid", false, List.of());
        }

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

        // Refund the previous highest bidder
        if (highestBidOpt.isPresent()) {
            refundBidder(highestBidOpt.get());
        }

        // Save the new bid
        bids newBid = new bids();
        newBid.setUser_id(userId);
        newBid.setAuction_id(carId);
        newBid.setBid_amount(bidAmount);
        newBid.setBid_time(new Date());
        bidsRepo.save(newBid);

        // Update the car's price to the highest bid
        updateCarPrice(carId, bidAmount);

        return message.MessageResponse("Bid placed successfully", true, List.of());
    }

    private void refundBidder(bids bid) {
        // Refund the previous highest bidder
        users previousBidder = userInformationRepo.findById(bid.getUser_id()).orElse(null);
        if (previousBidder != null) {
            previousBidder.setBalance(previousBidder.getBalance() + bid.getBid_amount());
            userInformationRepo.save(previousBidder);
        }
    }

    private void updateCarPrice(Integer carId, double bidAmount) {
        // Fetch the car entity
        car_information car = carInformationRepo.findById(carId).orElse(null);
        if (car != null) {
            // Update the car's price to the highest bid
            car.setPrice((int)bidAmount);
            carInformationRepo.save(car);
        }
    }
}
