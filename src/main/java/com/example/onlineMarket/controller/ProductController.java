package com.example.onlineMarket.controller;

import com.example.onlineMarket.models.ProductModel;
import com.example.onlineMarket.models.CreateProductModel;
import com.example.onlineMarket.entity.Product;
import com.example.onlineMarket.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("new")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductModel productModel) {
        Product savedProduct = productService.createProduct(productModel);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("update")
    public ResponseEntity<Product> updateBrand(@RequestBody ProductModel productModel) {
        Product savedProduct = productService.updateProduct(productModel);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
    
    @GetMapping("get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long productId){
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId){
        productService.deleteProduct(productId);
        String jsonResponse = "{\"message\": \"data has been deleted\"}";
        return ResponseEntity.ok(jsonResponse);
    }

}