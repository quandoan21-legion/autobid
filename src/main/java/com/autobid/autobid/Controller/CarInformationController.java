// File: src/main/java/com/autobid/autobid/Controller/CarInformationController.java
package com.autobid.autobid.Controller;

import com.autobid.autobid.DTO.AdminReviewRequest;
import com.autobid.autobid.DTO.CarInformationDTO;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Service.CarInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class CarInformationController {
    @Autowired
    private CarInformationService carInformationService;

    @PostMapping("/listings/add-listing")
    public MessageFactory postCarDetails(@RequestBody CarInformationDTO carInformationDTO) {
        return carInformationService.saveDetails(carInformationDTO);
    }

    @GetMapping("/listings")
    public MessageFactory getAllCars() {
        return carInformationService.getAllCars();
    }

    @GetMapping("/listings/{id}")
    public MessageFactory getCarDetail(@PathVariable Integer id) {
        return carInformationService.getCarDetailById(id);
    }

    @GetMapping("/listings/ended")
    public MessageFactory getEndedCars() {
        return carInformationService.getEndedCars();
    }

    // File: src/main/java/com/autobid/autobid/Controller/CarInformationController.java
    @PutMapping("/listings/update-listing/{id}")
    public MessageFactory updateCarDetails(@PathVariable Integer id, @RequestBody CarInformationDTO carInformationDTO) {
        return carInformationService.updateCarDetails(id, carInformationDTO);
    }

    @GetMapping("/account/listings")
    public MessageFactory getListingsByUserId(@RequestParam("user_id") Integer userId) {
        return carInformationService.getListingsByUserId(userId);
    }

    @PutMapping("/admin/review-car/{carId}")
    public MessageFactory adminReviewCar(
            @PathVariable Integer carId,
            @RequestBody AdminReviewRequest adminReviewRequest) {
        return carInformationService.adminReviewCar(carId, adminReviewRequest);
    }
}