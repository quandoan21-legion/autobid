package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "car_images")
@Data
public class car_images {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "car_id")
    private int car_id;
    @Column(name = "image")
    private String image;
}
