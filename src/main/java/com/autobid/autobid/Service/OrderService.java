package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.Orders;
import com.autobid.autobid.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public void saveOrder(Orders order) {
        orderRepo.save(order);
    }
}