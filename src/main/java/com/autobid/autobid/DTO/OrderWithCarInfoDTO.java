package com.autobid.autobid.DTO;

import com.autobid.autobid.Entity.Orders;
import lombok.Data;

import java.util.Date;

@Data
public class OrderWithCarInfoDTO {
    private int id;
    private int user_id;
    private Date order_date;
    private double total_amount;
    private CarInformationDTO carInfo; // Changed from car_information to CarInformationDTO

    public OrderWithCarInfoDTO(Orders order, CarInformationDTO carInfo) {
        this.id = order.getId();
        this.user_id = order.getUserId();
        this.order_date = order.getOrderDate();
        this.total_amount = order.getTotalAmount();
        this.carInfo = carInfo;
    }
}
