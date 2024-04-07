package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.Brand;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public Brand createBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public Brand getBrandById(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(
                () -> new ResourceNotFoundException("Brands is not exists with the given id: " + brandId)
        );
    }

    public Brand updeteBrand(Brand newBrand) {
        brandRepository.findById(newBrand.getBrandId()).orElseThrow(
                () -> new ResourceNotFoundException("Brands is not exists with the given id: " + newBrand.getBrandId())
        );
        return brandRepository.save(newBrand);
    }

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    public Brand deleteBrand(Long brandId){
        Brand brand = brandRepository.findById(brandId).orElseThrow(
                () -> new ResourceNotFoundException("Brands is not exists with the given id: " + brandId)
        );
        brandRepository.deleteById(brandId);
        return brand;
    }
}
