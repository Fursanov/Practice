package com.example.onlineMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"order_items\"")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"order_item_id\"")
    private Long orderItemId;

    @Column(name = "\"quantity\"")
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"order_id\"")
    @JsonIgnoreProperties(value = {"orderItems", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"product_id\"")
    @JsonIgnoreProperties(value = {"orderItems", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Product product;

}