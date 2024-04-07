package com.example.onlineMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"orders\"")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"order_id\"")
    private Long orderId;

    @Column(name = "\"order_date\"")
    private Date orderDate;

    @Column(name = "\"total_amount\"")
    private Double totalAmount;

    @Column(name = "\"order_status\"")
    private String orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"user_id\"")
    @JsonIgnoreProperties(value = {"orders", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"order", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Set<OrderItem> orderItems;


}