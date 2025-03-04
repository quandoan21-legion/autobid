// File: `src/main/java/com/autobid/autobid/DTO/CarInformationDTO.java`
    package com.autobid.autobid.DTO;

    import lombok.Data;

    import java.util.Date;
    import java.util.List;

    @Data
    public class CarInformationDTO {
        private Integer user;
        private Integer year_model;
        private String make;
        private String model;
        private String description;
        private Integer starting_bid;
        private Date created_at;
        private boolean status;
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
        private List<String> images; // Add this field to include images
    }