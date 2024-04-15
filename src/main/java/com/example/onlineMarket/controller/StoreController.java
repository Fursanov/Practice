package com.example.onlineMarket.controller;

import com.example.onlineMarket.services.StoreService;
import com.example.onlineMarket.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    @Autowired
    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping("new")
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store savedStore = storeService.createStore(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    @PostMapping("products/update")
    public ResponseEntity<Store> updateStore(@RequestBody Store store) {
        Store savedStore = storeService.updateStoreProducts(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    @PostMapping("update")
    public ResponseEntity<Store> updateStoreProducts(@RequestBody Store store) {
        Store savedStore = storeService.updateStore(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public ResponseEntity<List<Store>> getAllStores(){
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.ok(stores);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable("id") Long storeId){
        Store store = storeService.getStoreById(storeId);
        return ResponseEntity.ok(store);
    }
    @DeleteMapping("delete/{id}")
    public  ResponseEntity<String> deleteStore(@PathVariable("id") Long storeId){
        storeService.deleteStore(storeId);
        String jsonResponse = "{\"message\": \"data has been deleted\"}";
        return ResponseEntity.ok(jsonResponse);
    }

}