package com.example.onlineMarket.repository;

import com.example.onlineMarket.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
