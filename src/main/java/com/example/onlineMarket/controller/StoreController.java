package com.example.onlineMarket.controller;

import com.example.onlineMarket.services.StoreService;
import com.example.onlineMarket.entity.Store;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("new")
    public ResponseEntity<Store> createStore(@RequestBody Store store) {
        Store savedStore = storeService.createStore(store);
        return new ResponseEntity<>(savedStore, HttpStatus.CREATED);
    }

    @PostMapping("update")
    public ResponseEntity<Store> updateStore(@RequestBody Store store) {
        Store savedStore = storeService.updeteStore(store);
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