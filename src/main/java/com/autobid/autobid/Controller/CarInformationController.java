package com.autobid.autobid.Controller;

import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Service.CarInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarInformationController {
    @Autowired
    private CarInformationService carInformationService;

    @PostMapping("/addCarInfo")
    public car_information postCarDetails(@RequestBody car_information car_information) {
        return carInformationService.saveDetails(car_information);
    }

    @GetMapping("/allCarInfo")
    public List<car_information> getCarsDetails() {
        return carInformationService.getAllCars();
    }
}
