package com.example.onlinebookstore.Mapper;

import com.example.onlinebookstore.DTO.OrderItemDTO;
import com.example.onlinebookstore.Entity.Book;
import com.example.onlinebookstore.Entity.Order;
import com.example.onlinebookstore.Entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemDTO toDTO(OrderItem orderItem){
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(orderItem.getId());
        dto.setOrderId(orderItem.getOrder().getId());
        dto.setBookId(orderItem.getBook().getId());
        dto.setQuantity(orderItem.getQuantity());
        dto.setPrice(orderItem.getPrice());
        return dto;
    }

    public OrderItem toEntity(OrderItemDTO orderItemDTO, Order order, Book book){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDTO.getId());
        orderItem.setOrder(order);
        orderItem.setBook(book);
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());
        return orderItem;
    }
}
