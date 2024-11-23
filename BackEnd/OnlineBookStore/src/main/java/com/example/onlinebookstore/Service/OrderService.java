package com.example.onlinebookstore.Service;

import com.example.onlinebookstore.DTO.OrderDTO;
import com.example.onlinebookstore.Entity.Order;
import com.example.onlinebookstore.Entity.User;
import com.example.onlinebookstore.Mapper.OrderMapper;
import com.example.onlinebookstore.Repository.OrderRepository;
import com.example.onlinebookstore.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserRepository userRepository;

    public List<OrderDTO> getAllOrders(){
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }
    public OrderDTO getOrderById(Long id){
        Order order = orderRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Order not found with id"));
        return orderMapper.toDTO(order);
    }

    public OrderDTO createOrder(OrderDTO orderDTO){
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(()-> new RuntimeException("User not found with id"));

        Order order = orderMapper.toEntity(orderDTO,user);
        return orderMapper.toDTO(orderRepository.save(order));
    }

    public OrderDTO updateOrder(Long id,OrderDTO orderDTO){
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(orderDTO.getStatus());
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setShippingAddress(orderDTO.getShippingAddress());

        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        order.setUser(user);
        return orderMapper.toDTO(orderRepository.save(order));
    }

    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
