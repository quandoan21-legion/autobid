// File: src/main/java/com/autobid/autobid/Service/CarInformationService.java
package com.autobid.autobid.Service;

import com.autobid.autobid.DTO.CarInformationDTO;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.CarInformationRepo;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;


@Service
public class CarInformationService {
    @Autowired
    private CarInformationRepo carInformationRepo;

    @Autowired
    private UserInformationRepo userInformationRepo;

    MessageFactory message = new MessageFactory();

    public MessageFactory saveDetails(CarInformationDTO carInformationDTO) {
        users user = userInformationRepo.findById(carInformationDTO.getUser()).orElse(null);
        if (user == null) {
            return message.MessageResponse("Invalid User ID", false, List.of());
        }

        car_information carInformation = new car_information();
        carInformation.setF_user_id(user);
        carInformation.setYear_model(carInformationDTO.getYearModel());
        carInformation.setMake(carInformationDTO.getMake());
        carInformation.setModel(carInformationDTO.getModel());
        carInformation.setDescription(carInformationDTO.getDescription());
        carInformation.setStarting_bid(carInformationDTO.getStartingBid());
        carInformation.setCreated_at(carInformationDTO.getCreatedAt());
        carInformation.setStart_time(carInformationDTO.getStartTime());
        carInformation.setEnd_time(carInformationDTO.getEndTime());
        car_information savedCarInformation = carInformationRepo.save(carInformation);

        return message.MessageResponse("Create new listings successfully", true, List.of(savedCarInformation));
    }

    public MessageFactory getCarDetailById(Integer id) {
        car_information car_detail = carInformationRepo.findById(id).orElse(null);
        if (car_detail == null) {
            return message.MessageResponse("Invalid car id", false, List.of());
        }
        return message.MessageResponse("This is your car detail", true, List.of(car_detail));
    }

    public MessageFactory getAllCars() {
        return message.MessageResponse("This is all car listings", true, List.of(carInformationRepo.findAll()));
    }
}