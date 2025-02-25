package com.autobid.autobid.Service;

import com.autobid.autobid.DTO.CarInformationDTO;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.CarInformationRepo;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        carInformation.setYear_model(carInformationDTO.getYear_model());
        carInformation.setMake(carInformationDTO.getMake());
        carInformation.setModel(carInformationDTO.getModel());
        carInformation.setDescription(carInformationDTO.getDescription());
        carInformation.setStarting_bid(carInformationDTO.getStarting_bid());
        carInformation.setCreated_at(carInformationDTO.getCreated_at());
        carInformation.setStart_time(carInformationDTO.getStart_time());
        carInformation.setEnd_time(carInformationDTO.getEnd_time());
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
        Date currentDate = new Date();
        List<car_information> cars = carInformationRepo.findAllByEndTimeAfter(currentDate);
        return message.MessageResponse("This is all car listings", true, List.of(cars));
    }

    public MessageFactory getEndedCars() {
        Date currentDate = new Date();
        List<car_information> cars = carInformationRepo.findAllByEndTimeBeforeOrEqual(currentDate);
        return message.MessageResponse("This is all ended car listings", true, List.of(cars));
    }
}