package com.example.onlineMarket.models;

import com.example.onlineMarket.entity.Brand;
import com.example.onlineMarket.entity.Order;
import com.example.onlineMarket.entity.Product;
import com.example.onlineMarket.entity.User;
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
public class ProductReviewModel {
    private Long productReviewId;
    private int rating;
    private String reviewText;
    private Long userId;
    private Long productId;
}
