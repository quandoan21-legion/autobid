// File: src/main/java/com/autobid/autobid/Service/BidService.java
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

import java.util.Date;
import java.util.List;

@Service
public class BidService {
    @Autowired
    private BidsRepo bidsRepo;

    @Autowired
    private CarInformationRepo carInformationRepo;

    @Autowired
    private UserInformationRepo userInformationRepo;

    @Autowired
    private UserInformationService userInformationService;

    MessageFactory message = new MessageFactory();

    public MessageFactory placeBid(Integer carId, Integer userId, double bidAmount) {
        car_information car = carInformationRepo.findById(carId).orElse(null);
        if (car == null) {
            return message.MessageResponse("Invalid car ID", false, List.of());
        }

        users user = userInformationRepo.findById(userId).orElse(null);
        if (user == null) {
            return message.MessageResponse("Invalid user ID", false, List.of());
        }

        if (user.getBalance() < bidAmount) {
            return message.MessageResponse("Insufficient balance", false, List.of());
        }

        userInformationService.deductUserBalance(userId, bidAmount);

        bids newBid = new bids();
        newBid.setF_car_information(car);
        newBid.setF_user_id(user);
        newBid.setBid_amount(bidAmount);
        newBid.setBid_time(new Date());
        bids savedBid = bidsRepo.save(newBid);

        return message.MessageResponse("Bid placed successfully", true, List.of(savedBid));
    }
}