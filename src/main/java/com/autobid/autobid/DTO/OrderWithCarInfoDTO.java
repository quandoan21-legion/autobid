package com.autobid.autobid.DTO;

import com.autobid.autobid.Entity.Orders;
import lombok.Data;

import java.util.Date;

// In OrderWithCarInfoDTO.java
@Data
public class OrderWithCarInfoDTO {
    private int id;
    private int user_id;
    private Date order_date;
    private double total_amount;
    private double commission;  // Add this field
    private CarInformationDTO carInfo;

    public OrderWithCarInfoDTO(Orders order, CarInformationDTO carInfo) {
        this.id = order.getId();
        this.user_id = order.getUserId();
        this.order_date = order.getOrderDate();
        this.total_amount = order.getTotalAmount();
        this.commission = order.getCommission();  // Add this line
        this.carInfo = carInfo;
    }
}
