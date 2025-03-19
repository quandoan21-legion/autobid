package com.autobid.autobid.Controller;

import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/account/orders")
    // api list order tuong ung voi nguoi dung. FE truyen user_id vao
    public MessageFactory list(@RequestHeader Integer user_id) {
        return orderService.list(user_id);
    }

    @GetMapping("/account/orders/{order_id}")
    public MessageFactory getDetail(@PathVariable Integer order_id, @RequestHeader Integer user_id) {
        return orderService.getDetail(order_id, user_id);
    }
}
