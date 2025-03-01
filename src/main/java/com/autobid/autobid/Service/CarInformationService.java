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
        carInformation.setStatus(carInformationDTO.isStatus());
        carInformation.setStart_time(carInformationDTO.getStart_time());
        carInformation.setEnd_time(carInformationDTO.getEnd_time());
        carInformation.setVIN(carInformationDTO.getVIN());
        carInformation.setMileage(carInformationDTO.getMileage());
        carInformation.setInterial_color(carInformationDTO.getInterial_color());
        carInformation.setExterior_color(carInformationDTO.getExterior_color());
        carInformation.setEngine(carInformationDTO.getEngine());
        carInformation.setDrive_type(carInformationDTO.getDrive_type());
        carInformation.setBody_style(carInformationDTO.getBody_style());
        carInformation.setDoors(carInformationDTO.getDoors());
        carInformation.setCondition(carInformationDTO.getCondition());
        carInformation.setPrice(carInformationDTO.getPrice());
        carInformation.setLocation(carInformationDTO.getLocation());
        carInformation.setTransmission(carInformationDTO.getTransmission());
        carInformation.setFuel_type(carInformationDTO.getFuel_type());
        carInformation.setModifications(carInformationDTO.getModifications());
        carInformation.setFlaws(carInformationDTO.getFlaws());
        carInformation.setEquipment(carInformationDTO.getEquipment());
        car_information savedCarInformation = carInformationRepo.save(carInformation);

        return message.MessageResponse("Create new listings successfully", true, List.of(savedCarInformation));
    }

    public MessageFactory updateCarDetails(Integer id, CarInformationDTO carInformationDTO) {
        car_information carInformation = carInformationRepo.findById(id).orElse(null);
        if (carInformation == null) {
            return message.MessageResponse("Invalid car ID", false, List.of());
        }

        users user = userInformationRepo.findById(carInformationDTO.getUser()).orElse(null);
        if (user == null) {
            return message.MessageResponse("Invalid User ID", false, List.of());
        }

        if (carInformationDTO.getYear_model() != null) {
            carInformation.setYear_model(carInformationDTO.getYear_model());
        }
        if (carInformationDTO.getMake() != null) {
            carInformation.setMake(carInformationDTO.getMake());
        }
        if (carInformationDTO.getModel() != null) {
            carInformation.setModel(carInformationDTO.getModel());
        }
        if (carInformationDTO.getDescription() != null) {
            carInformation.setDescription(carInformationDTO.getDescription());
        }
        if (carInformationDTO.getStarting_bid() != null) {
            carInformation.setStarting_bid(carInformationDTO.getStarting_bid());
        }
        if (carInformationDTO.getCreated_at() != null) {
            carInformation.setCreated_at(carInformationDTO.getCreated_at());
        }
        carInformation.setStatus(carInformationDTO.isStatus());
        if (carInformationDTO.getStart_time() != null) {
            carInformation.setStart_time(carInformationDTO.getStart_time());
        }
        if (carInformationDTO.getEnd_time() != null) {
            carInformation.setEnd_time(carInformationDTO.getEnd_time());
        }
        if (carInformationDTO.getVIN() != null) {
            carInformation.setVIN(carInformationDTO.getVIN());
        }
        if (carInformationDTO.getMileage() != null) {
            carInformation.setMileage(carInformationDTO.getMileage());
        }
        if (carInformationDTO.getInterial_color() != null) {
            carInformation.setInterial_color(carInformationDTO.getInterial_color());
        }
        if (carInformationDTO.getExterior_color() != null) {
            carInformation.setExterior_color(carInformationDTO.getExterior_color());
        }
        if (carInformationDTO.getEngine() != null) {
            carInformation.setEngine(carInformationDTO.getEngine());
        }
        if (carInformationDTO.getDrive_type() != null) {
            carInformation.setDrive_type(carInformationDTO.getDrive_type());
        }
        if (carInformationDTO.getBody_style() != null) {
            carInformation.setBody_style(carInformationDTO.getBody_style());
        }
        if (carInformationDTO.getDoors() != null) {
            carInformation.setDoors(carInformationDTO.getDoors());
        }
        if (carInformationDTO.getCondition() != null) {
            carInformation.setCondition(carInformationDTO.getCondition());
        }
        if (carInformationDTO.getPrice() != null) {
            carInformation.setPrice(carInformationDTO.getPrice());
        }
        if (carInformationDTO.getLocation() != null) {
            carInformation.setLocation(carInformationDTO.getLocation());
        }
        if (carInformationDTO.getTransmission() != null) {
            carInformation.setTransmission(carInformationDTO.getTransmission());
        }
        if (carInformationDTO.getFuel_type() != null) {
            carInformation.setFuel_type(carInformationDTO.getFuel_type());
        }
        if (carInformationDTO.getModifications() != null) {
            carInformation.setModifications(carInformationDTO.getModifications());
        }
        if (carInformationDTO.getFlaws() != null) {
            carInformation.setFlaws(carInformationDTO.getFlaws());
        }
        if (carInformationDTO.getEquipment() != null) {
            carInformation.setEquipment(carInformationDTO.getEquipment());
        }

        car_information updatedCarInformation = carInformationRepo.save(carInformation);

        return message.MessageResponse("Car listing updated successfully", true, List.of(updatedCarInformation));
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