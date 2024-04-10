package com.example.onlineMarket.models;

import lombok.*;

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
