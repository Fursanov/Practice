package com.example.onlineMarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String username;
    private String email;
    private String password_hash;
    private String role;
}
