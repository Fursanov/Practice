package com.example.onlineMarket.service.impl;

import com.example.onlineMarket.dto.UserDto;
import com.example.onlineMarket.entity.User;
import com.example.onlineMarket.exception.ResourceNotFoundException;
import com.example.onlineMarket.mapper.UserMapper;
import com.example.onlineMarket.repository.UserRepository;
import com.example.onlineMarket.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto){
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Users is not exists with the given id: " + userId)
        );
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map((UserMapper::mapToUserDto))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long userId, UserDto updatedUser){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Users is not exists with the given id: " + userId)
        );

        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setRole(updatedUser.getRole());
        user.setPasswordHash(updatedUser.getPassword_hash());

        userRepository.save(user);
        User updatedUserObj = userRepository.save(user);
        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("Users is not exists with the given id: " + userId)
        );
        userRepository.deleteById(userId);
    }
}
