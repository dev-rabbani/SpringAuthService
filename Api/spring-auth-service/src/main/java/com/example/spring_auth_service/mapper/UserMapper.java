package com.example.spring_auth_service.mapper;

import com.example.spring_auth_service.model.dto.request.UserRegistrationRequest;
import com.example.spring_auth_service.model.dto.response.RegisteredUserResponse;
import com.example.spring_auth_service.model.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userRegistrationRequestToUser(UserRegistrationRequest request);
    RegisteredUserResponse userToRegisteredUserResponse(User user);
}
