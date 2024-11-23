package com.example.onlinebookstore.Service;

import com.example.onlinebookstore.DTO.OrderItemDTO;
import com.example.onlinebookstore.Entity.Book;
import com.example.onlinebookstore.Entity.Order;
import com.example.onlinebookstore.Entity.OrderItem;
import com.example.onlinebookstore.Mapper.OrderItemMapper;
import com.example.onlinebookstore.Repository.BookRepository;
import com.example.onlinebookstore.Repository.OrderItemRepository;
import com.example.onlinebookstore.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<OrderItemDTO> getAllOrderItems(){
        return orderItemRepository.findAll()
                .stream()
                .map(orderItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public OrderItemDTO getOrderItemById(Long id){
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id"));
        return orderItemMapper.toDTO(orderItem);
    }

    public OrderItemDTO createOrderItem(OrderItemDTO orderItemDTO){
        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Book book = bookRepository.findById(orderItemDTO.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found"));


        OrderItem orderItem = orderItemMapper.toEntity(orderItemDTO,order,book);
        return orderItemMapper.toDTO(orderItemRepository.save(orderItem));
    }

    public OrderItemDTO updateOrderItem(Long id, OrderItemDTO orderItemDTO){
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Orderitem not found"));
        orderItem.setQuantity(orderItemDTO.getQuantity());
        orderItem.setPrice(orderItemDTO.getPrice());

        Order order = orderRepository.findById(orderItemDTO.getOrderId())
                .orElseThrow(()-> new RuntimeException("Order not found"));
        orderItem.setOrder(order);

        Book book = bookRepository.findById(orderItemDTO.getBookId())
                .orElseThrow(()->new RuntimeException("Book not found"));
        orderItem.setBook(book);
        return orderItemMapper.toDTO(orderItemRepository.save(orderItem));
    }

    public void deleteOrderItem(Long id){
        orderItemRepository.deleteById(id);
    }
}
