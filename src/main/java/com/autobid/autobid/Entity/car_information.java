package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "car_information")
public class car_information {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "year")
    private Integer year_model;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "starting_bid")
    private Integer starting_bid;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    // Change status to an enum
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('in_progress', 'approved', 'rejected')")
    private CarStatus status;

    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;

    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_time;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private users f_user_id;

    @Column(name = "VIN")
    private String VIN;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "interial_color")
    private String interial_color;

    @Column(name = "exterior_color")
    private String exterior_color;

    @Column(name = "engine")
    private String engine;

    @Column(name = "drive_type")
    private String drive_type;

    @Column(name = "body_style")
    private String body_style;

    @Column(name = "doors")
    private Integer doors;

    @Column(name = "`condition`")
    private String condition;

    @Column(name = "price")
    private Integer price;

    @Column(name = "location")
    private String location;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "fuel_type")
    private String fuel_type;

    @Column(name = "equipment")
    private String equipment;

    @Column(name = "flaws")
    private String flaws;

    @Column(name = "modifications")
    private String modifications;

    @Column(name = "admin_message")
    private String admin_message;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<car_images> images;
}
