package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.orders;
import com.autobid.autobid.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public void saveOrder(orders order) {
        orderRepo.save(order);
    }
}