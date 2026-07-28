package com.example.product_service.mapper;

import com.example.product_service.dto.UserResponseDto;
import com.example.product_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDto toResponseDto(User user){

        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());
      return dto;
    }
}
