package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.*;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.models.CreateProductModel;
import com.example.onlineMarket.models.ProductModel;
import com.example.onlineMarket.repository.ProductRepository;
import com.example.onlineMarket.repository.BrandRepository;
import com.example.onlineMarket.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;
    private final StoreRepository storeRepository;
    static final String resourceNotFoundException = "Products is not exists with the given id: ";

    @Autowired
    public ProductService(
            ProductRepository productRepository,
            BrandRepository brandRepository,
            StoreRepository storeRepository
    ) {
        this.productRepository = productRepository;
        this.brandRepository = brandRepository;
        this.storeRepository = storeRepository;
    }

    public Product createProduct(CreateProductModel productModel){

        Brand brand = brandRepository.findById(productModel.getBrandId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + productModel.getBrandId())
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
                () -> new ResourceNotFoundException(resourceNotFoundException + productId)
        );
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public void deleteProduct(Long productId){
        productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + productId)
        );
        productRepository.deleteById(productId);
    }

    public Product updateProduct(ProductModel newProduct) {
        Brand brand = brandRepository.findById(newProduct.getBrand().getBrandId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newProduct.getBrand().getBrandId())
        );
        List<Store> stores = null;
        if(newProduct.getStoresId() != null) {
            stores = storeRepository.findAllById(newProduct.getStoresId());
        }
        Product oldProduct = productRepository.findById(newProduct.getProductId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newProduct.getProductId())
        );
        oldProduct.setBrand(brand);
        oldProduct.setStores(stores);
        oldProduct.setName(newProduct.getName());
        oldProduct.setDescription(newProduct.getDescription());
        oldProduct.setPrice(newProduct.getPrice());
        oldProduct.setStockQuantity(newProduct.getStockQuantity());
        return productRepository.save(oldProduct);
    }
}
