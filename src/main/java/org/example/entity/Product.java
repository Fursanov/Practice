package org.example.entity;

public class Product {
    private final Long productId;
    private final String name;
    private final String description;
    private final Double price;
    private final int stockQuantity;
    private final Long brandId;

    public Product(Long productId, String name, String description, Double price, int stockQuantity, Long brandId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.brandId = brandId;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public Long getBrandId() {
        return brandId;
    }
}
