package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final Long storeId;
    private final String storeName;
    private final String location;
    private final List<Long> productIds;

    public Store(Long storeId, String storeName, String location) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.location = location;
        this.productIds = new ArrayList<>();
    }

    public Long getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getLocation() {
        return location;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void addProductId(Long productId) {
        this.productIds.add(productId);
    }
}
