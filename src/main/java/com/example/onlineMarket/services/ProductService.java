package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.*;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.models.ProductModel;
import com.example.onlineMarket.repository.ProductRepository;
import com.example.onlineMarket.repository.BrandRepository;
import com.example.onlineMarket.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    StoreRepository storeRepository;

    public Product createProduct(ProductModel productModel){

        Brand brand = brandRepository.findById(productModel.getBrandId()).orElseThrow(
                () -> new ResourceNotFoundException("Products is not exists with the given id: " + productModel.getBrandId())
        );
        List<Store> stores = storeRepository.findAllById(productModel.getStoresId());
        Product product = new Product(
                null,
                productModel.getName(),
                productModel.getDescription(),
                productModel.getPrice(),
                productModel.getStockQuantity(),
                brand,
                stores,
                null,
                null
        );
        return productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Products is not exists with the given id: " + productId)
        );
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product deleteProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Products is not exists with the given id: " + productId)
        );
        productRepository.deleteById(productId);
        return product;
    }

    public Product updeteProduct(
            Long productId, Long brandId, Set<Long> storesId,
            String name, String description, double price, int quantity) {
        Brand brand = brandRepository.findById(brandId).orElseThrow(
                () -> new ResourceNotFoundException("Products is not exists with the given id: " + brandId)
        );
        List<Store> stores = null;
        if(storesId != null) {
            stores = storeRepository.findAllById(storesId);
        }
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Products is not exists with the given id: " + productId)
        );
        product.setBrand(brand);
        product.setStores(stores);
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStockQuantity(quantity);
        return productRepository.save(product);
    }
}
