package com.example.onlinebookstore.Repository;

import com.example.onlinebookstore.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
