package com.e.order_sevice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e.order_sevice.models.Order;

public interface OrderRepo extends JpaRepository<Order,Long> {
    
}
