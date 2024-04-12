package com.example.onlineMarket.repository;

import com.example.onlineMarket.entity.Product;
import com.example.onlineMarket.entity.ProductReview;
import com.example.onlineMarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
    ProductReview getProductReviewByProductAndUser(Product product, User user);
}
