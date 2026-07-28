package com.example.product_service.service;

import com.example.product_service.dto.LoginRequestDto;
import com.example.product_service.dto.LoginResponseDto;
import com.example.product_service.dto.RegisterRequestDto;
import com.example.product_service.dto.UserResponseDto;
import com.example.product_service.entity.User;
import com.example.product_service.exception.UserAlreadyExistsException;
import com.example.product_service.mapper.UserMapper;
import com.example.product_service.repository.UserRepository;
import com.example.product_service.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper mapper;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public UserResponseDto register(RegisterRequestDto register) {
        if(repository.findByUsername(register.getUsername()).isPresent()){
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(
                passwordEncoder.encode(register.getPassword())
        );
        user.setRole("USER");
        User save = repository.save(user);
        return mapper.toResponseDto(save);
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails userDetails =
                customUserDetailsService
                        .loadUserByUsername(request.getUsername());

        String token =
                jwtService.generateToken(userDetails);

        return new LoginResponseDto(token);
    }
}
