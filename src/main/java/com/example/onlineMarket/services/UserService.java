package com.example.onlineMarket.services;

import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceAlreadyExistException;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    static final String resourceNotFoundException = "Users is not exists with the given id: ";
    static final String resourceAlreadyExistException = "User with that email already exist: ";

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        if(userRepository.getUserByEmail(user.getEmail()) == null)
            return userRepository.save(user);
        else
            throw new ResourceAlreadyExistException(
                    resourceAlreadyExistException + user.getEmail());
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException(resourceNotFoundException + userId)
        );
    }

    public User updateUser(User newUser) {
        User searchUser = userRepository.getUserByEmail(newUser.getEmail());
        if(searchUser == null || Objects.equals(searchUser.getUserId(), newUser.getUserId())) {
            User oldUser = userRepository.findById(newUser.getUserId()).orElseThrow(
                    () -> new ResourceNotFoundException(resourceNotFoundException + newUser.getUserId())
            );
            oldUser.setUserName(newUser.getUserName());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setPasswordHash(newUser.getPasswordHash());
            oldUser.setRole(newUser.getRole());
            return userRepository.save(oldUser);
        }
        else
            throw new ResourceAlreadyExistException(
                    resourceAlreadyExistException + newUser.getEmail());
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
