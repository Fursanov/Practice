package com.example.onlineMarket.controller;

import com.example.onlineMarket.entity.ProductReview;
import com.example.onlineMarket.entity.ProductReview;
import com.example.onlineMarket.models.ProductReviewModel;
import com.example.onlineMarket.services.ProductReviewService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/productReviews")
public class ProductReviewController {

    @Autowired
    private ProductReviewService productReviewService;

    @PostMapping("new")
    public ResponseEntity<ProductReview> createProductReview(@RequestBody ProductReviewModel productReviewModel) {
        ProductReview savedProductReview = productReviewService.createProductReview(productReviewModel);
        return new ResponseEntity<>(savedProductReview, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<ProductReview>> getAllProductReviews(){
        List<ProductReview> productReviews = productReviewService.getAllProductReviews();
        return ResponseEntity.ok(productReviews);
    }

    @PostMapping("update")
    public ResponseEntity<ProductReview> updateProductReview(@RequestBody ProductReview productReview) {
        ProductReview savedProductReview = productReviewService.updeteProductReview(productReview);
        return new ResponseEntity<>(savedProductReview, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<ProductReview> getProductReviewById(@PathVariable("id") Long productReviewId){
        ProductReview productReview = productReviewService.getProductReviewById(productReviewId);
        return ResponseEntity.ok(productReview);
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteProductReview(@PathVariable("id") Long productReviewId){
        productReviewService.deleteProductReview(productReviewId);
        String jsonResponse = "{\"message\": \"data has been deleted\"}";
        return ResponseEntity.ok(jsonResponse);
    }

}