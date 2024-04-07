package com.example.onlineMarket.controller;

import com.example.onlineMarket.entity.Brand;
import com.example.onlineMarket.services.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("new")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brands) {
        Brand savedBrand = brandService.createBrand(brands);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Brand>> getAllBrands(){
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @PostMapping("update")
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
        Brand savedBrand = brandService.updeteBrand(brand);
        return new ResponseEntity<>(savedBrand, HttpStatus.CREATED);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable("id") Long brandId){
        Brand brand = brandService.getBrandById(brandId);
        return ResponseEntity.ok(brand);
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteBrand(@PathVariable("id") Long brandId){
        brandService.deleteBrand(brandId);
        String jsonResponse = "{\"message\": \"data has been deleted\"}";
        return ResponseEntity.ok(jsonResponse);
    }

}