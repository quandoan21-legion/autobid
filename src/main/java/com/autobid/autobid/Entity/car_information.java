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
    //    @Column(name = "user_id", insertable = false, updatable = false)
//    private Integer user_id;
//    @Column(name = "order_id", insertable = false, updatable = false)
//    private Integer order_id;
    @Column(name = "year_model")
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
    @Column(name = "image_id", insertable = false, updatable = false)
    private Integer image_id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;
    @Column(name = "status")
    private boolean status;
    @Column(name = "start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date start_time;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_time;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private users f_user_id;

//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_id", referencedColumnName = "id")
//    private List<orders> f_order_id;

    @OneToMany
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private List<car_images> f_image_id;
}