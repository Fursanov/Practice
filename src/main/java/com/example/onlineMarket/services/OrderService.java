package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.Brand;
import com.example.onlineMarket.entity.Order;
import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.OrderRepository;
import com.example.onlineMarket.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;

    public Order createOrder(Long userId){

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Orders is not exists with the given id: " + userId)
        );
        Order order = new Order(
                null,
                new Date(),
                0.0,
                "выбор",
                user,
                null
                );
        return orderRepository.save(order);
    }

    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Orders is not exists with the given id: " + orderId)
        );
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order deleteOrder(Long orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Orders is not exists with the given id: " + orderId)
        );
        orderRepository.deleteById(orderId);
        return order;
    }

    public Order updeteOrder(Long userId, Date orderDate, Double totalAmount, String orderStatus, Long orderId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Orders is not exists with the given id: " + userId)
        );
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("Orders is not exists with the given id: " + orderId)
        );
        order.setUser(user);
        order.setOrderDate(orderDate);
        order.setTotalAmount(totalAmount);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }
}
