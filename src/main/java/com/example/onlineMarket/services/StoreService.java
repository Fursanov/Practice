package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.Store;
import com.example.onlineMarket.exception.ResourceAlreadyExistException;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    static final String resourceNotFoundException = "Stores is not exists with the given id: ";
    static final String resourceAlreadyExistException = "Store with that name and location already exist: ";

    @Autowired
    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }


    public Store createStore(Store store){
        if(storeRepository.findStoreByStoreNameAndLocation(store.getStoreName(), store.getLocation()) == null)
            return storeRepository.save(store);
        else
            throw new ResourceAlreadyExistException(
                    resourceAlreadyExistException + store.getStoreName() + " (" + store.getLocation() + ")");
    }

    public Store getStoreById(Long storeId) {
        return storeRepository.findById(storeId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + storeId)
        );
    }

    public Store updateStore(Store newStore) {
        Store searchStore = storeRepository.findStoreByStoreNameAndLocation(newStore.getStoreName(), newStore.getLocation());
        if(searchStore == null || Objects.equals(searchStore.getStoreId(), newStore.getStoreId())) {
            Store oldStore = storeRepository.findById(newStore.getStoreId()).orElseThrow(
                    () -> new ResourceNotFoundException(resourceNotFoundException + newStore.getStoreId())
            );
            oldStore.setStoreName(newStore.getStoreName());
            oldStore.setLocation(newStore.getLocation());
            return storeRepository.save(oldStore);
        }
        else
            throw new ResourceAlreadyExistException(
                    resourceAlreadyExistException + newStore.getStoreName() + " (" + newStore.getLocation() + ")");
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
