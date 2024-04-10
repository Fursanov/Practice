package com.example.onlineMarket.models;

import com.example.onlineMarket.entity.User;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateOrderModel {
    private Long orderId;
    private Date orderDate;
    private Double totalAmount;
    private String orderStatus;
    private User user;
}