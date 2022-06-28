package com.CC.MoviesSystem.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.dto.LoginDTO;
import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.exception.ExpiredTokenException;
import com.CC.MoviesSystem.exception.InvalidLoginException;
import com.CC.MoviesSystem.exception.InvalidTokenException;
import com.CC.MoviesSystem.exception.NonexistentEmailException;
import com.CC.MoviesSystem.repository.UserRepository;

import io.jsonwebtoken.Claims;

@Service
public class UserAuthenticationService {
    
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserAuthenticationService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    public User authenticate(LoginDTO data, String token) {
        User user = userRepository.findByEmail(data.getEmail())
            .orElseThrow(() -> new NonexistentEmailException());
        if(data.getPassword().equals(user.getPassword()) && !token.isEmpty() && validate(token)) {
            return user;
        } else {
            throw new InvalidLoginException();
        }

    }

    private boolean validate(String token) {
        try {
            String tokenTratado = token.replace("Bearer ", "");
            Claims claims = tokenService.decodeToken(tokenTratado);
            if (claims.getExpiration().before(new Date(System.currentTimeMillis()))) throw new ExpiredTokenException();
            return true;
        } catch (ExpiredTokenException et){
            et.printStackTrace();
            throw et;
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidTokenException();
        }

    }
}
