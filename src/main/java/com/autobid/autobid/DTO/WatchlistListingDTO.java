package com.autobid.autobid.DTO;

import com.autobid.autobid.Entity.car_images;
import com.autobid.autobid.Entity.car_information;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class WatchlistListingDTO {
    private Integer id;
    private Integer year_model;
    private String make;
    private String model;
    private String description;
    private Integer starting_bid;
    private String status;
    private List<String> images;

    // Constructor to convert car_information to WatchlistListingDTO
    public WatchlistListingDTO(car_information car) {
        this.id = car.getId();
        this.year_model = car.getYear_model();
        this.make = car.getMake();
        this.model = car.getModel();
        this.description = car.getDescription();
        this.starting_bid = car.getStarting_bid();
        this.status = car.getStatus().toString();
        this.images = car.getImages().stream()
                .map(car_images::getImage)
                .collect(Collectors.toList());
    }
}
