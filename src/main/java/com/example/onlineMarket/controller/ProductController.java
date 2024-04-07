package com.example.onlineMarket.controller;

import com.example.onlineMarket.models.ProductModel;
import com.example.onlineMarket.services.ProductService;
import com.example.onlineMarket.entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("new")
    public ResponseEntity<Product> createProduct(@RequestBody ProductModel productModel) {
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
        Product savedProduct = productService.updeteProduct(
                productModel.getProductId(), productModel.getBrandId(), productModel.getStoresId(),
                productModel.getName(), productModel.getDescription(), productModel.getPrice(),
                productModel.getStockQuantity()
        );
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