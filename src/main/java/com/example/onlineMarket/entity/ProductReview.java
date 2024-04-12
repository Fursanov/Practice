package com.example.onlineMarket.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"product_reviews\"")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"review_id\"")
    private Long productReviewId;

    @Column(name = "\"rating\"")
    private int rating;

    @Column(name = "\"review_text\"")
    private String reviewText;

    @Column(name = "\"review_date\"")
    private Date reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"customer_id\"")
    @JsonIgnoreProperties(value = {"productReviews", "orders", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"product_id\"")
    @JsonIgnoreProperties(value = {"productReviews", "orderItems", "handler", "hibernateLazyInitializer"}, allowSetters = true)
    private Product product;

}