// File: `src/main/java/com/autobid/autobid/Service/CarInformationService.java`
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
import org.springframework.transaction.annotation.Transactional;

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

    private final MessageFactory message = new MessageFactory();

    @Transactional
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

        CarInformationDTO responseDTO = convertToDTO(savedCarInformation);
        return message.MessageResponse("Create new listings successfully", true, List.of(responseDTO));
    }

    private CarInformationDTO convertToDTO(car_information car) {
        List<String> images = carImagesRepo.findByCar(car.getId())
                .stream()
                .map(car_images::getImage)
                .collect(Collectors.toList());

        CarInformationDTO carDTO = new CarInformationDTO();
        carDTO.setId(car.getId());
        carDTO.setUser(car.getF_user_id().getId());
        carDTO.setYear_model(car.getYear_model());
        carDTO.setMake(car.getMake());
        carDTO.setModel(car.getModel());
        carDTO.setDescription(car.getDescription());
        carDTO.setStarting_bid(car.getStarting_bid());
        carDTO.setCreated_at(car.getCreated_at());
        carDTO.setStatus(car.isStatus());
        carDTO.setStart_time(car.getStart_time());
        carDTO.setEnd_time(car.getEnd_time());
        carDTO.setVIN(car.getVIN());
        carDTO.setMileage(car.getMileage());
        carDTO.setInterial_color(car.getInterial_color());
        carDTO.setExterior_color(car.getExterior_color());
        carDTO.setEngine(car.getEngine());
        carDTO.setDrive_type(car.getDrive_type());
        carDTO.setBody_style(car.getBody_style());
        carDTO.setDoors(car.getDoors());
        carDTO.setCondition(car.getCondition());
        carDTO.setPrice(car.getPrice());
        carDTO.setLocation(car.getLocation());
        carDTO.setTransmission(car.getTransmission());
        carDTO.setFuel_type(car.getFuel_type());
        carDTO.setModifications(car.getModifications());
        carDTO.setFlaws(car.getFlaws());
        carDTO.setEquipment(car.getEquipment());
        carDTO.setImages(images);

        return carDTO;
    }

    @Transactional
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
        if (carInformationDTO.getImages() != null) {
            // Delete existing images in a transaction
            carImagesRepo.deleteByCar(carInformation.getId());
            // Save new images
            for (String imageUrl : carInformationDTO.getImages()) {
                car_images carImage = new car_images();
                carImage.setCar(carInformation.getId());
                carImage.setImage(imageUrl);
                carImagesRepo.save(carImage);
            }
        }
        car_information updatedCarInformation = carInformationRepo.save(carInformation);
        CarInformationDTO responseDTO = convertToDTO(updatedCarInformation);
        return message.MessageResponse("Car listing updated successfully", true, List.of(responseDTO));
    }

    public MessageFactory getCarDetailById(Integer id) {
        car_information car_detail = carInformationRepo.findById(id).orElse(null);
        if (car_detail == null) {
            return message.MessageResponse("Invalid car id", false, List.of());
        }

        CarInformationDTO responseDTO = convertToDTO(car_detail);
        return message.MessageResponse("This is your car detail", true, List.of(responseDTO));
    }

    public MessageFactory getAllCars() {
        Date currentDate = new Date();
        List<car_information> cars = carInformationRepo.findAllByEndTimeAfter(currentDate);

        List<CarInformationDTO> carDTOs = cars.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return message.MessageResponse("This is all car listings", true, carDTOs);
    }

    public MessageFactory getEndedCars() {
        Date currentDate = new Date();
        List<car_information> cars = carInformationRepo.findAllByEndTimeBeforeOrEqual(currentDate);

        List<CarInformationDTO> carDTOs = cars.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return message.MessageResponse("This is all ended car listings", true, carDTOs);
    }

    public boolean isCarOwner(Integer carId, Integer userId) {
        car_information car = carInformationRepo.findById(carId).orElse(null);
        return car != null && car.getF_user_id().getId() == userId;
    }
}