// File: src/main/java/com/autobid/autobid/DTO/CarInformationDTO.java
package com.autobid.autobid.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CarInformationDTO {
    private Integer user;
    private Integer year_model;
    private String make;
    private String model;
    private String description;
    private Integer starting_bid;
    private Date created_at;
    private Date start_time;
    private Date end_time;
}