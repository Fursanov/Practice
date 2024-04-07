package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Users is not exists with the given id: " + userId)
        );
    }

    public User updeteUser(User newUser) {
        userRepository.findById(newUser.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException("Users is not exists with the given id: " + newUser.getUserId())
        );
        return userRepository.save(newUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Users is not exists with the given id: " + userId)
        );
        userRepository.deleteById(userId);
        return user;
    }
}
