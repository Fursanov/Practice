package com.example.onlineMarket.repository;

import com.example.onlineMarket.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
    Store findStoreByStoreNameAndLocation(String name, String location);
}
