package com.example.onlineMarket.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemModel {
    private Long orderItemId;
    private Long orderId;
    private Long productId;
    private int quantity;
}
