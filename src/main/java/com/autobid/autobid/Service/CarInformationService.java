package com.autobid.autobid.Service;

import com.autobid.autobid.DTO.CarInformationDTO;
import com.autobid.autobid.Entity.car_images;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.CarImagesRepo;
import com.autobid.autobid.Repository.CarInformationRepo;
import com.autobid.autobid.Repository.UserInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarInformationService {
    @Autowired
    private CarInformationRepo carInformationRepo;

    @Autowired
    private CarImagesRepo carImagesRepo;

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
        carInformation.setCreated_at(new Date());
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

        if (carInformationDTO.getImages() != null) {
            for (String imageUrl : carInformationDTO.getImages()) {
                car_images carImage = new car_images();
                carImage.setCar(savedCarInformation.getId());
                carImage.setImage(imageUrl);
                carImagesRepo.save(carImage);
            }
        }

        List<String> images = carImagesRepo.findByCar(savedCarInformation.getId())
                .stream()
                .map(car_images::getImage)
                .collect(Collectors.toList());

        CarInformationDTO responseDTO = new CarInformationDTO();
        responseDTO.setUser(savedCarInformation.getF_user_id().getId());
        responseDTO.setYear_model(savedCarInformation.getYear_model());
        responseDTO.setMake(savedCarInformation.getMake());
        responseDTO.setModel(savedCarInformation.getModel());
        responseDTO.setDescription(savedCarInformation.getDescription());
        responseDTO.setStarting_bid(savedCarInformation.getStarting_bid());
        responseDTO.setCreated_at(savedCarInformation.getCreated_at());
        responseDTO.setStatus(savedCarInformation.isStatus());
        responseDTO.setStart_time(savedCarInformation.getStart_time());
        responseDTO.setEnd_time(savedCarInformation.getEnd_time());
        responseDTO.setVIN(savedCarInformation.getVIN());
        responseDTO.setMileage(savedCarInformation.getMileage());
        responseDTO.setInterial_color(savedCarInformation.getInterial_color());
        responseDTO.setExterior_color(savedCarInformation.getExterior_color());
        responseDTO.setEngine(savedCarInformation.getEngine());
        responseDTO.setDrive_type(savedCarInformation.getDrive_type());
        responseDTO.setBody_style(savedCarInformation.getBody_style());
        responseDTO.setDoors(savedCarInformation.getDoors());
        responseDTO.setCondition(savedCarInformation.getCondition());
        responseDTO.setPrice(savedCarInformation.getPrice());
        responseDTO.setLocation(savedCarInformation.getLocation());
        responseDTO.setTransmission(savedCarInformation.getTransmission());
        responseDTO.setFuel_type(savedCarInformation.getFuel_type());
        responseDTO.setModifications(savedCarInformation.getModifications());
        responseDTO.setFlaws(savedCarInformation.getFlaws());
        responseDTO.setEquipment(savedCarInformation.getEquipment());
        responseDTO.setImages(images);

        return message.MessageResponse("Create new listings successfully", true, List.of(responseDTO));
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

        List<String> images = carImagesRepo.findByCar(updatedCarInformation.getId())
                .stream()
                .map(car_images::getImage)
                .collect(Collectors.toList());

        CarInformationDTO responseDTO = new CarInformationDTO();
        responseDTO.setUser(updatedCarInformation.getF_user_id().getId());
        responseDTO.setYear_model(updatedCarInformation.getYear_model());
        responseDTO.setMake(updatedCarInformation.getMake());
        responseDTO.setModel(updatedCarInformation.getModel());
        responseDTO.setDescription(updatedCarInformation.getDescription());
        responseDTO.setStarting_bid(updatedCarInformation.getStarting_bid());
        responseDTO.setCreated_at(updatedCarInformation.getCreated_at());
        responseDTO.setStatus(updatedCarInformation.isStatus());
        responseDTO.setStart_time(updatedCarInformation.getStart_time());
        responseDTO.setEnd_time(updatedCarInformation.getEnd_time());
        responseDTO.setVIN(updatedCarInformation.getVIN());
        responseDTO.setMileage(updatedCarInformation.getMileage());
        responseDTO.setInterial_color(updatedCarInformation.getInterial_color());
        responseDTO.setExterior_color(updatedCarInformation.getExterior_color());
        responseDTO.setEngine(updatedCarInformation.getEngine());
        responseDTO.setDrive_type(updatedCarInformation.getDrive_type());
        responseDTO.setBody_style(updatedCarInformation.getBody_style());
        responseDTO.setDoors(updatedCarInformation.getDoors());
        responseDTO.setCondition(updatedCarInformation.getCondition());
        responseDTO.setPrice(updatedCarInformation.getPrice());
        responseDTO.setLocation(updatedCarInformation.getLocation());
        responseDTO.setTransmission(updatedCarInformation.getTransmission());
        responseDTO.setFuel_type(updatedCarInformation.getFuel_type());
        responseDTO.setModifications(updatedCarInformation.getModifications());
        responseDTO.setFlaws(updatedCarInformation.getFlaws());
        responseDTO.setEquipment(updatedCarInformation.getEquipment());
        responseDTO.setImages(images);

        return message.MessageResponse("Car listing updated successfully", true, List.of(responseDTO));
    }

    public MessageFactory getCarDetailById(Integer id) {
        car_information car_detail = carInformationRepo.findById(id).orElse(null);
        if (car_detail == null) {
            return message.MessageResponse("Invalid car id", false, List.of());
        }

        List<String> images = carImagesRepo.findByCar(car_detail.getId())
                .stream()
                .map(car_images::getImage)
                .collect(Collectors.toList());

        CarInformationDTO responseDTO = new CarInformationDTO();
        responseDTO.setUser(car_detail.getF_user_id().getId());
        responseDTO.setYear_model(car_detail.getYear_model());
        responseDTO.setMake(car_detail.getMake());
        responseDTO.setModel(car_detail.getModel());
        responseDTO.setDescription(car_detail.getDescription());
        responseDTO.setStarting_bid(car_detail.getStarting_bid());
        responseDTO.setCreated_at(car_detail.getCreated_at());
        responseDTO.setStatus(car_detail.isStatus());
        responseDTO.setStart_time(car_detail.getStart_time());
        responseDTO.setEnd_time(car_detail.getEnd_time());
        responseDTO.setVIN(car_detail.getVIN());
        responseDTO.setMileage(car_detail.getMileage());
        responseDTO.setInterial_color(car_detail.getInterial_color());
        responseDTO.setExterior_color(car_detail.getExterior_color());
        responseDTO.setEngine(car_detail.getEngine());
        responseDTO.setDrive_type(car_detail.getDrive_type());
        responseDTO.setBody_style(car_detail.getBody_style());
        responseDTO.setDoors(car_detail.getDoors());
        responseDTO.setCondition(car_detail.getCondition());
        responseDTO.setPrice(car_detail.getPrice());
        responseDTO.setLocation(car_detail.getLocation());
        responseDTO.setTransmission(car_detail.getTransmission());
        responseDTO.setFuel_type(car_detail.getFuel_type());
        responseDTO.setModifications(car_detail.getModifications());
        responseDTO.setFlaws(car_detail.getFlaws());
        responseDTO.setEquipment(car_detail.getEquipment());
        responseDTO.setImages(images);

        return message.MessageResponse("This is your car detail", true, List.of(responseDTO));
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