package com.example.onlineMarket.controller;

import com.example.onlineMarket.entity.Brand;
import com.example.onlineMarket.models.OrderModel;
import com.example.onlineMarket.models.UpdateOrderModel;
import com.example.onlineMarket.services.OrderService;
import com.example.onlineMarket.entity.Order;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("new")
    public ResponseEntity<Order> createOrder(@RequestBody OrderModel orderModel) {
        Order savedOrder = orderService.createOrder(orderModel.getUserId());
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

    @PostMapping("update")
    public ResponseEntity<Order> updateOrder(@RequestBody UpdateOrderModel orderModel) {
        Order savedOrder = orderService.updeteOrder(orderModel.getUser().getUserId(), orderModel.getOrderDate(), orderModel.getTotalAmount(), orderModel.getOrderStatus(), orderModel.getOrderId());
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