package com.autobid.autobid.Service;

import com.autobid.autobid.Entity.orders;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    public MessageFactory list(Integer userId) {
        List<orders> orders = orderRepo.findAllByUserId(userId);
        MessageFactory messageFactory = new MessageFactory();
        messageFactory.setMessage("successful get orders");
        messageFactory.setSuccess(true);
        messageFactory.setData(orders);
        return messageFactory;
    }

    public MessageFactory getDetail(Integer orderId, Integer userId) {
        List<orders> orders = orderRepo.findByIdAndUserId(orderId, userId);
        MessageFactory messageFactory = new MessageFactory();
        messageFactory.setMessage("successful get orders");
        messageFactory.setSuccess(true);
        messageFactory.setData(orders);
        return messageFactory;
    }
}
