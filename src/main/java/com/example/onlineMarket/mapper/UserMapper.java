package com.example.onlineMarket.mapper;

import com.example.onlineMarket.dto.UserDto;
import com.example.onlineMarket.entity.User;

public class UserMapper {

    public static UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getRole()
        );
    }

    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPassword_hash(),
                userDto.getRole()
        );
    }
}
