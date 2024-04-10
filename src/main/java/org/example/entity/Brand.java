package org.example.entity;

public class Brand {
    private final Long brandId;
    private final String brandName;

    public Brand(Long brandId, String brandName) {
        this.brandId = brandId;
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

}