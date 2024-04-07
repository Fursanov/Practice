package com.example.onlineMarket.repository;

import com.example.onlineMarket.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
