package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.Brand;
import com.example.onlineMarket.exception.ResourceAlreadyExistException;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BrandService {


    private final BrandRepository brandRepository;
    static final String resourceNotFoundException = "Brands is not exists with the given id: ";
    static final String resourceAlreadyExistException = "Brand with that name already exist: ";

    @Autowired
    public BrandService(BrandRepository brandRepository){
        this.brandRepository = brandRepository;
    }

    public Brand createBrand(Brand brand){
        if(brandRepository.findByBrandName(brand.getBrandName()) == null)
            return brandRepository.save(brand);
        else
            throw new ResourceAlreadyExistException(resourceAlreadyExistException + brand.getBrandName());
    }

    public Brand getBrandById(Long brandId) {
        return brandRepository.findById(brandId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + brandId)
        );
    }

    public Brand updateBrand(Brand newBrand) {
        Brand searchBrand = brandRepository.findByBrandName(newBrand.getBrandName());
        if(searchBrand == null || Objects.equals(searchBrand.getBrandId(), newBrand.getBrandId())) {
            Brand oldBrand = brandRepository.findById(newBrand.getBrandId()).orElseThrow(
                    () -> new ResourceNotFoundException(resourceNotFoundException + newBrand.getBrandId())
            );
            oldBrand.setBrandName(newBrand.getBrandName());
            return brandRepository.save(oldBrand);
        }
        else
            throw new ResourceAlreadyExistException(resourceAlreadyExistException + newBrand.getBrandName());
    }

    public List<Brand> getAllBrands(){
        return brandRepository.findAll();
    }

    public void deleteBrand(Long brandId){
        brandRepository.findById(brandId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + brandId)
        );
        brandRepository.deleteById(brandId);
    }
}
