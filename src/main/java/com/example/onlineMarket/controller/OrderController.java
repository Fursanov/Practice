package com.example.onlineMarket.controller;

import com.example.onlineMarket.models.OrderModel;
import com.example.onlineMarket.models.UpdateOrderModel;
import com.example.onlineMarket.services.OrderService;
import com.example.onlineMarket.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("new")
    public ResponseEntity<Order> createOrder(@RequestBody OrderModel orderModel) {
        Order savedOrder = orderService.createOrder(orderModel.getUserId());
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("update")
    public ResponseEntity<Order> updateOrder(@RequestBody UpdateOrderModel orderModel) {
        Order savedOrder = orderService.updateOrder(orderModel);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable("id") Long orderId){
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(order);
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId){
        orderService.deleteOrder(orderId);
        String jsonResponse = "{\"message\": \"data has been deleted\"}";
        return ResponseEntity.ok(jsonResponse);
    }

}