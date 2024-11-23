package com.example.onlinebookstore.Controller;

import com.example.onlinebookstore.DTO.OrderItemDTO;
import com.example.onlinebookstore.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public List<OrderItemDTO> getAllOrderItems(){
        return orderItemService.getAllOrderItems();
    }

    @GetMapping("/{id}")
    public OrderItemDTO getOrderItemById(@PathVariable Long id) {
        return orderItemService.getOrderItemById(id);
    }

    @PostMapping
    public OrderItemDTO createOrderItem(@RequestBody OrderItemDTO orderItemDTO){
        return orderItemService.createOrderItem(orderItemDTO);
    }

    @PutMapping("/{id}")
    public OrderItemDTO updateOrderItem(@PathVariable Long id,@RequestBody OrderItemDTO orderItemDTO) {
        return orderItemService.updateOrderItem(id,orderItemDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderItem(@PathVariable Long id){
        orderItemService.deleteOrderItem(id);
    }
}
