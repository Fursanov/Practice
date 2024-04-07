package com.example.onlineMarket.models;

import com.example.onlineMarket.entity.Brand;
import com.example.onlineMarket.entity.Order;
import com.example.onlineMarket.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Set;

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
