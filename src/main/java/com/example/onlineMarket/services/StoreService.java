package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.Store;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class StoreService {

    @Autowired
    StoreRepository storeRepository;


    public Store createStore(Store store){
        return storeRepository.save(store);
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new ResourceNotFoundException("Stores is not exists with the given id: " + storeId)
        );
    }

    public Store updeteStore(Store newStore) {
        storeRepository.findById(newStore.getStoreId()).orElseThrow(
                () -> new ResourceNotFoundException("Stores is not exists with the given id: " + newStore.getStoreId())
        );
        return storeRepository.save(newStore);
    }

    public List<Store> getAllStores(){
        return storeRepository.findAll();
    }

    public Store deleteStore(Long storeId){
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new ResourceNotFoundException("Stores is not exists with the given id: " + storeId)
        );
        storeRepository.deleteById(storeId);
        return store;
    }
}
