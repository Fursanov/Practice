package com.example.onlineMarket.models;

import com.example.onlineMarket.entity.Brand;
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
public class ProductModel {
    private Long productId;
    private String name;
    private String description;
    private Double price;
    private int stockQuantity;
    private Long brandId;
    private Set<Long> storesId;
}
