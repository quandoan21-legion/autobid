package com.autobid.autobid.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class CarInformationDTO {
    private Integer user;
    private Integer yearModel;
    private String make;
    private String model;
    private String description;
    private Integer startingBid;
    private Date createdAt;
    private Date startTime;
    private Date endTime;
}