package com.autobid.autobid.DTO;

import com.autobid.autobid.Entity.CarStatus;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CarInformationDTO {
    private Integer id;
    private Integer user;
    private Integer year_model;
    private String make;
    private String model;
    private String description;
    private Integer starting_bid;
    private Date created_at;
    private CarStatus status; // Change to enum
    private Date start_time;
    private Date end_time;
    private Integer user_id;
    private String VIN;
    private Integer mileage;
    private String interial_color;
    private String exterior_color;
    private String engine;
    private String drive_type;
    private String body_style;
    private Integer doors;
    private String condition;
    private Integer price;
    private String location;
    private String transmission;
    private String fuel_type;
    private String modifications;
    private String flaws;
    private String equipment;
    private List<String> images;
    private List<CommentDTO> comments;
    private String admin_message; // Add this field
    private String username; // User's username
    private String email; // User's email
    private String imageUrl;

    // Getters and setters for all fields
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
