package com.example.onlineMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"products\"")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"product_id\"")
    private Long productId;

    @Column(name = "\"name\"")
    private String name;

    @Column(name = "\"description\"")
    private String description;

    @Column(name = "\"price\"")
    private Double price;

    @Column(name = "\"stock_quantity\"")
    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"brand_id\"")
    @JsonIgnoreProperties(value = {"products", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Brand brand;

    @ManyToMany(mappedBy = "products")
    @JsonIgnoreProperties(value = {"products", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private List<Store> stores;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"product", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Set<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value = {"product", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Set<ProductReview> productReviews;

    @PreRemove
    private void beforeDelete() {
        for (Store store : stores)
            store.getProducts().remove(this);
    }

}