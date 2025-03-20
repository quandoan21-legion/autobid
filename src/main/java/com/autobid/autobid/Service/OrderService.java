package com.autobid.autobid.Service;

import com.autobid.autobid.DTO.CarInformationDTO;
import com.autobid.autobid.DTO.OrderWithCarInfoDTO;
import com.autobid.autobid.Entity.Orders;
import com.autobid.autobid.Entity.car_information;
import com.autobid.autobid.Entity.users;
import com.autobid.autobid.Factory.MessageFactory;
import com.autobid.autobid.Repository.OrderRepo;
import com.autobid.autobid.Repository.UserInformationRepo;
import com.autobid.autobid.Repository.CarInformationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

// In OrderService.java
@Service
public class OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserInformationRepo userInformationRepo;

    @Autowired
    private CarInformationRepo carInformationRepo;

    public MessageFactory getAllOrders(Integer adminId) {
        // Check if user is admin
        Optional<users> adminUser = userInformationRepo.findById(adminId);
        if (adminUser.isEmpty() || !adminUser.get().isAdmin()) {
            return new MessageFactory().MessageResponse("Unauthorized access", false, List.of());
        }

        // Get all orders
        List<Orders> allOrders = orderRepo.findAllByOrderByOrderDateDesc();

        // Convert orders to DTOs with car information
        List<OrderWithCarInfoDTO> orderDTOs = allOrders.stream()
                .map(order -> {
                    car_information carInfo = carInformationRepo.findById(order.getAuctionId()).orElse(null);
                    if (carInfo != null) {
                        CarInformationDTO carInfoDTO = convertToCarInfoDTO(carInfo);
                        return new OrderWithCarInfoDTO(order, carInfoDTO);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return new MessageFactory().MessageResponse("All orders retrieved successfully", true, orderDTOs);
    }

    private CarInformationDTO convertToCarInfoDTO(car_information carInfo) {
        CarInformationDTO dto = new CarInformationDTO();
        dto.setId(carInfo.getId());
        dto.setMake(carInfo.getMake());
        dto.setModel(carInfo.getModel());
        dto.setYear_model(carInfo.getYear_model());
        // Set other relevant car information fields
        return dto;
    }

    public void saveOrder(Orders order) {
        orderRepo.save(order);
    }
}
