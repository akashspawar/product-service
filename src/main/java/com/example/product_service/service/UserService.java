package com.example.product_service.service;

import com.example.product_service.dto.LoginRequestDto;
import com.example.product_service.dto.LoginResponseDto;
import com.example.product_service.dto.RegisterRequestDto;
import com.example.product_service.dto.UserResponseDto;

public interface UserService {

    //User
    UserResponseDto register(RegisterRequestDto register);

    LoginResponseDto login(LoginRequestDto request);
}
