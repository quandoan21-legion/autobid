package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "car_images")
@Data
public class car_images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "car")
    private int car;
    @Column(name = "image")
    private String image;
}
