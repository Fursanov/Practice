package com.example.onlineMarket.models;

import com.example.onlineMarket.entity.Brand;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductModel {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private int stockQuantity;
    private Brand brand;
    private Set<Long> storesId;
}
