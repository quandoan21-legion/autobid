package com.autobid.autobid.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "auctions")
public class auctions {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "start_time")
    private Date start_time;
    @Column(name = "end_time")
    private Date end_time;
    @Column(name = "owner_id", insertable = false, updatable = false)
    private int owner_id;
    @Column(name = "car_id", insertable = false, updatable = false)
    private int car_id;

    @OneToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private car_information f_car_information_id;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private users f_user_id;

}
