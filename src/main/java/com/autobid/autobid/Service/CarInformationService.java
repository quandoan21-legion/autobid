package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Repository.CarInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarInformationService {
    @Autowired
    private CarInformationRepo carInformationRepo;

    public car_information saveDetails(car_information car_information) {
        return carInformationRepo.save(car_information);
    }

    public List<car_information> getAllCars(){
        return carInformationRepo.findAll();
    }
}
