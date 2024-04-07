package com.example.onlineMarket.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderModel {
    private Long orderId;
    private Long userId;
}
