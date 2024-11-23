package com.example.onlinebookstore.Mapper;

import com.example.onlinebookstore.DTO.OrderDTO;
import com.example.onlinebookstore.Entity.Order;
import com.example.onlinebookstore.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderDTO toDTO(Order order){
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());
        dto.setPaymentStatus(order.getPaymentStatus());
        dto.setShippingAddress(order.getShippingAddress());
        return dto;
    }

    public Order toEntity(OrderDTO orderDTO, User user){
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setUser(user);
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(orderDTO.getStatus());
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setShippingAddress(orderDTO.getShippingAddress());
        return order;
    }
}
