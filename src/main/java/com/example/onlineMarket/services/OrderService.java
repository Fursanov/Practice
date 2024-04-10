package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.Order;
import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.models.UpdateOrderModel;
import com.example.onlineMarket.repository.OrderRepository;
import com.example.onlineMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {


    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    static final String resourceNotFoundException = "Orders is not exists with the given id: ";

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order createOrder(Long userId){

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + userId)
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
                () -> new ResourceNotFoundException(resourceNotFoundException + orderId)
        );
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public void deleteOrder(Long orderId){
        orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + orderId)
        );
        orderRepository.deleteById(orderId);
    }

    public Order updateOrder(UpdateOrderModel newOrder) {
        User user = userRepository.findById(newOrder.getUser().getUserId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newOrder.getUser().getUserId())
        );
        Order oldOrder = orderRepository.findById(newOrder.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newOrder.getOrderId())
        );
        oldOrder.setUser(user);
        oldOrder.setOrderDate(newOrder.getOrderDate());
        oldOrder.setTotalAmount(newOrder.getTotalAmount());
        oldOrder.setOrderStatus(newOrder.getOrderStatus());
        return orderRepository.save(oldOrder);
    }
}
