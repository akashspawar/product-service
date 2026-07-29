package com.example.product_service.controller;

import com.example.product_service.dto.*;
import com.example.product_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> requestDto(@Valid @RequestBody RegisterRequestDto request){

        UserResponseDto user = service.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseBuilder.success(
                        "User registered successfully",
                        user,
                        HttpStatus.CREATED
                ));

    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto request) {

        LoginResponseDto response =
                service.login(request);

        return ResponseEntity.ok(
                ResponseBuilder.success(
                        "Login successful",
                        response,
                        HttpStatus.OK
                )
        );
    }
}
