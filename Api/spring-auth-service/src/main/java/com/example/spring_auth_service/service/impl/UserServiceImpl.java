package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.model.enums.ExceptionConstant;
import com.example.spring_auth_service.model.enums.RedisKey;
import com.example.spring_auth_service.repository.UserRepository;
import com.example.spring_auth_service.service.CacheService;
import com.example.spring_auth_service.service.UserService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final CacheService cacheService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username);
    }

    @Override
    public User save(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException(ExceptionConstant.USERNAME_EXISTS.getMessage());
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException(ExceptionConstant.USER_EMAIL_EXISTS.getMessage());
        }

        User savedUser = userRepository.save(user);
        cacheService.put(getRedisKey(savedUser.getUsername()), savedUser);

        return savedUser;
    }

    @Override
    public User findByUsername(String username) {
        String redisKey = getRedisKey(username);

        Optional<User> userOptional = cacheService.get(redisKey, User.class);

        return userOptional.orElseGet(() -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException(username));

            cacheService.put(redisKey, user);
            return user;
        });
    }

    private String getRedisKey(String username) {
        return String.format(RedisKey.USER_ENTITY.getKey(), username);
    }
}
