package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    static final String resourceNotFoundException = "Users is not exists with the given id: ";

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + userId)
        );
    }

    public User updateUser(User newUser) {
        User oldUser = userRepository.findById(newUser.getUserId()).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + newUser.getUserId())
        );
        oldUser.setUserName(newUser.getUserName());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setPasswordHash(newUser.getPasswordHash());
        oldUser.setRole(newUser.getRole());
        return userRepository.save(oldUser);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long userId){
        userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + userId)
        );
        userRepository.deleteById(userId);
    }
}
