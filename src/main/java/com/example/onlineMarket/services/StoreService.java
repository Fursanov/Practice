package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.Store;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    static final String resourceNotFoundException = "Stores is not exists with the given id: ";

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    public Store createStore(Store store){
        return storeRepository.save(store);
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + storeId)
        );
    }

    public Store updateStore(Store newStore) {
        Store oldStore = storeRepository.findById(newStore.getStoreId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newStore.getStoreId())
        );
        oldStore.setStoreName(newStore.getStoreName());
        oldStore.setLocation(newStore.getLocation());
        return storeRepository.save(oldStore);
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    public void deleteStore(Long storeId){
        storeRepository.findById(storeId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + storeId)
        );
        storeRepository.deleteById(storeId);
    }
}
