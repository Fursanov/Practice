package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.*;
import com.example.onlineMarket.entity.OrderItem;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.models.OrderItemModel;
import com.example.onlineMarket.models.OrderItemModel;
import com.example.onlineMarket.repository.OrderItemRepository;
import com.example.onlineMarket.repository.OrderRepository;
import com.example.onlineMarket.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public OrderItem createOrderItem(OrderItemModel orderItemModel){

        Order order = orderRepository.findById(orderItemModel.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("OrderItems is not exists with the given id: " + orderItemModel.getOrderId())
        );
        Product product = productRepository.findById(orderItemModel.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("OrderItems is not exists with the given id: " + orderItemModel.getProductId())
        );
        OrderItem orderItem = new OrderItem(
                null,
                orderItemModel.getQuantity(),
                order,
                product
        );
        return orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItemById(Long orderItemId) {
        return orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new ResourceNotFoundException("OrderItems is not exists with the given id: " + orderItemId)
        );
    }

    public OrderItem updeteOrderItem(OrderItem newOrderItem) {
        orderItemRepository.findById(newOrderItem.getOrderItemId()).orElseThrow(
                () -> new ResourceNotFoundException("OrderItems is not exists with the given id: " + newOrderItem.getOrderItemId())
        );
        return orderItemRepository.save(newOrderItem);
    }

    public List<OrderItem> getAllOrderItems(){
        return orderItemRepository.findAll();
    }

    public OrderItem deleteOrderItem(Long orderItemId){
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new ResourceNotFoundException("OrderItems is not exists with the given id: " + orderItemId)
        );
        orderItemRepository.deleteById(orderItemId);
        return orderItem;
    }
}
