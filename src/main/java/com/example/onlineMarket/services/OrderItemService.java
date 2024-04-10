package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.*;
import com.example.onlineMarket.entity.OrderItem;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.models.OrderItemModel;
import com.example.onlineMarket.repository.OrderItemRepository;
import com.example.onlineMarket.repository.OrderRepository;
import com.example.onlineMarket.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    static final String resourceNotFoundException = "OrderItems is not exists with the given id: ";

    @Autowired
    public OrderItemService(
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository
    ) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderItem createOrderItem(OrderItemModel orderItemModel){

        Order order = orderRepository.findById(orderItemModel.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + orderItemModel.getOrderId())
        );
        Product product = productRepository.findById(orderItemModel.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + orderItemModel.getProductId())
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
                () -> new ResourceNotFoundException(resourceNotFoundException + orderItemId)
        );
    }

    public OrderItem updateOrderItem(OrderItem newOrderItem) {
        OrderItem oldOrderItem = orderItemRepository.findById(newOrderItem.getOrderItemId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newOrderItem.getOrderItemId())
        );
        oldOrderItem.setOrder(newOrderItem.getOrder());
        oldOrderItem.setProduct(newOrderItem.getProduct());
        oldOrderItem.setQuantity(newOrderItem.getQuantity());
        return orderItemRepository.save(oldOrderItem);
    }

    public List<OrderItem> getAllOrderItems(){
        return orderItemRepository.findAll();
    }

    public void deleteOrderItem(Long orderItemId){
        orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + orderItemId)
        );
        orderItemRepository.deleteById(orderItemId);
    }
}
