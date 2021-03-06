package com.CC.Authentication.service;

import org.springframework.stereotype.Service;

import com.CC.Authentication.entity.User;
import com.CC.Authentication.exception.ExistentEmailException;
import com.CC.Authentication.repository.UserRepository;

@Service
public class UserRegistrationService {
    
    private final UserRepository userRepository;
    private TokenService tokenService;

    public UserRegistrationService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User registrate(User user) {
        if (userRepository.existsByEmail(user.getEmail())) throw new ExistentEmailException();
        user.setToken(tokenService.generateToken(user));
        return userRepository.save(user);
    }
}
