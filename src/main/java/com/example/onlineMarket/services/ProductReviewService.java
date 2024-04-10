package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.ProductReview;
import com.example.onlineMarket.entity.Product;
import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.models.ProductReviewModel;
import com.example.onlineMarket.repository.ProductRepository;
import com.example.onlineMarket.repository.ProductReviewRepository;
import com.example.onlineMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductReviewService {

    private final ProductReviewRepository productReviewRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    static final String resourceNotFoundException = "ProductReviews is not exists with the given id: ";

    @Autowired
    public ProductReviewService(
            ProductReviewRepository productReviewRepository,
            UserRepository userRepository,
            ProductRepository productRepository
    ) {
        this.productReviewRepository = productReviewRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public ProductReview createProductReview(ProductReviewModel productReviewModel){

        User user = userRepository.findById(productReviewModel.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + productReviewModel.getUserId())
        );
        Product product = productRepository.findById(productReviewModel.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + productReviewModel.getProductId())
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
                () -> new ResourceNotFoundException(resourceNotFoundException + productReviewId)
        );
    }

    public ProductReview updateProductReview(ProductReview newProductReview) {
        ProductReview oldProductReview = productReviewRepository.findById(newProductReview.getProductReviewId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newProductReview.getProductReviewId())
        );
        oldProductReview.setRating(newProductReview.getRating());
        oldProductReview.setReviewText(newProductReview.getReviewText());
        oldProductReview.setReviewDate(newProductReview.getReviewDate());
        oldProductReview.setUser(newProductReview.getUser());
        oldProductReview.setProduct(newProductReview.getProduct());
        return productReviewRepository.save(oldProductReview);
    }

    public List<ProductReview> getAllProductReviews(){
        return productReviewRepository.findAll();
    }

    public void deleteProductReview(Long productReviewId){
        productReviewRepository.findById(productReviewId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + productReviewId)
        );
        productReviewRepository.deleteById(productReviewId);
    }
}
