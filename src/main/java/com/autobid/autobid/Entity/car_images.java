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

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", insertable = false, updatable = false)
    private car_information car;

    @Column(name = "car_id")
    private int carId;

    @Column(name = "image")
    private String image;
}
