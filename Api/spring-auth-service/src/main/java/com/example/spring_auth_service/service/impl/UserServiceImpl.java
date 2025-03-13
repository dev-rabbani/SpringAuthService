package com.example.spring_auth_service.service.impl;

import com.example.spring_auth_service.model.entity.User;
import com.example.spring_auth_service.model.enums.ExceptionConstant;
import com.example.spring_auth_service.model.enums.RedisKey;
import com.example.spring_auth_service.repository.UserRepository;
import com.example.spring_auth_service.service.CacheService;
import com.example.spring_auth_service.service.UserService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.spring_auth_service.model.enums.ExceptionConstant.USER_NOT_FOUND;

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
    public User create(User user) {
        if(userRepository.existsByUsername(user.getUsername())) {
            throw new EntityExistsException(ExceptionConstant.USERNAME_EXISTS.getMessage());
        }

        if(userRepository.existsByEmail(user.getEmail())) {
            throw new EntityExistsException(ExceptionConstant.USER_EMAIL_EXISTS.getMessage());
        }

        return save(user);
    }

    @Override
    public User update(User user) {
        return save(user);
    }

    @Override
    public User findByUsername(String username) {
        String redisKey = getRedisKey(username);

        Optional<User> userOptional = cacheService.get(redisKey, User.class);

        return userOptional.orElseGet(() -> {
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException(USER_NOT_FOUND.getMessage()));

            cacheService.put(redisKey, user);
            return user;
        });
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private User save(User user) {
        User savedUser = userRepository.save(user);
        cacheService.put(getRedisKey(savedUser.getUsername()), savedUser);

        return savedUser;
    }

    private String getRedisKey(String username) {
        return String.format(RedisKey.USER_ENTITY.getKey(), username);
    }
}
