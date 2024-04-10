package com.example.onlineMarket.models;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateProductModel {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private int stockQuantity;
    private Long brandId;
    private Set<Long> storesId;
}
