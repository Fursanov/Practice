package com.example.onlineMarket.controller;

import com.example.onlineMarket.entity.OrderItem;
import com.example.onlineMarket.models.OrderItemModel;
import com.example.onlineMarket.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderItems")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @PostMapping("new")
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemModel orderItemModel) {
        OrderItem savedOrderItem = orderItemService.createOrderItem(orderItemModel);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<OrderItem>> getAllOrderItems(){
        List<OrderItem> orderItems = orderItemService.getAllOrderItems();
        return ResponseEntity.ok(orderItems);
    }

    @PostMapping("update")
    public ResponseEntity<OrderItem> updateOrderItem(@RequestBody OrderItem orderItem) {
        OrderItem savedOrderItem = orderItemService.updateOrderItem(orderItem);
        return new ResponseEntity<>(savedOrderItem, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable("id") Long orderItemId){
        OrderItem orderItem = orderItemService.getOrderItemById(orderItemId);
        return ResponseEntity.ok(orderItem);
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteOrderItem(@PathVariable("id") Long orderItemId){
        orderItemService.deleteOrderItem(orderItemId);
        String jsonResponse = "{\"message\": \"data has been deleted\"}";
        return ResponseEntity.ok(jsonResponse);
    }

}