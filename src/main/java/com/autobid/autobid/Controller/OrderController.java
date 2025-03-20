package com.autobid.autobid.Controller;

import com.autobid.autobid.DTO.CarInformationDTO;
import com.autobid.autobid.DTO.OrderWithCarInfoDTO;
import com.autobid.autobid.Entity.Orders;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.CarInformationRepo;
import com.autobid.autobid.Repository.OrderRepo;
import com.autobid.autobid.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CarInformationRepo carInformationRepo;

    @GetMapping("/orders")
    public MessageFactory getOrdersByUser(@RequestParam("user_id") int user_id) {  // Keeping request parameter in snake_case
        List<Orders> userOrders = orderRepo.findByUserId(user_id);  // Updated method call

        List<OrderWithCarInfoDTO> orderDTOs = userOrders.stream()
                .map(order -> {
                    car_information carInfo = carInformationRepo.findById(order.getAuctionId()).orElse(null);
                    if (carInfo != null) {
                        // Assuming OrderWithCarInfoDTO constructor expects Orders and CarInformationDTO
                        CarInformationDTO carInfoDTO = new CarInformationDTO();
                        return new OrderWithCarInfoDTO(order, carInfoDTO);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new MessageFactory().MessageResponse("Orders retrieved successfully", true, orderDTOs);
    }

    @GetMapping("/admin/orders")
    public MessageFactory getAllOrders(@RequestParam("admin_id") Integer adminId) {
        return orderService.getAllOrders(adminId);
    }
}
