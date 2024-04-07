package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.ProductReview;
import com.example.onlineMarket.entity.Product;
import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.models.ProductReviewModel;
import com.example.onlineMarket.repository.ProductRepository;
import com.example.onlineMarket.repository.ProductReviewRepository;
import com.example.onlineMarket.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductReviewService {

    @Autowired
    ProductReviewRepository productReviewRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public ProductReview createProductReview(ProductReviewModel productReviewModel){

        User user = userRepository.findById(productReviewModel.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("ProductReviews is not exists with the given id: " + productReviewModel.getUserId())
        );
        Product product = productRepository.findById(productReviewModel.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException("ProductReviews is not exists with the given id: " + productReviewModel.getProductId())
        );
        ProductReview productReview = new ProductReview(
                null,
                productReviewModel.getRating(),
                productReviewModel.getReviewText(),
                new Date(),
                user,
                product

        );
        return productReviewRepository.save(productReview);
    }

    public ProductReview getProductReviewById(Long productReviewId) {
        return productReviewRepository.findById(productReviewId).orElseThrow(
                () -> new ResourceNotFoundException("ProductReviews is not exists with the given id: " + productReviewId)
        );
    }

    public ProductReview updeteProductReview(ProductReview newProductReview) {
        productReviewRepository.findById(newProductReview.getProductReviewId()).orElseThrow(
                () -> new ResourceNotFoundException("ProductReviews is not exists with the given id: " + newProductReview.getProductReviewId())
        );
        return productReviewRepository.save(newProductReview);
    }

    public List<ProductReview> getAllProductReviews(){
        return productReviewRepository.findAll();
    }

    public ProductReview deleteProductReview(Long productReviewId){
        ProductReview productReview = productReviewRepository.findById(productReviewId).orElseThrow(
                () -> new ResourceNotFoundException("ProductReviews is not exists with the given id: " + productReviewId)
        );
        productReviewRepository.deleteById(productReviewId);
        return productReview;
    }
}
