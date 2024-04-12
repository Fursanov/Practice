package com.example.onlineMarket.repository;

import com.example.onlineMarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getProductByName(String name);
}
