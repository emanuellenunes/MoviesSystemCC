package com.CC.Authentication.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.CC.Authentication.dto.LoginDTO;
import com.CC.Authentication.entity.User;
import com.CC.Authentication.exception.ExpiredTokenException;
import com.CC.Authentication.exception.InvalidLoginException;
import com.CC.Authentication.exception.InvalidTokenException;
import com.CC.Authentication.exception.NonexistentEmailException;
import com.CC.Authentication.repository.UserRepository;

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
        if(data.getPassword().equals(user.getPassword()) && !token.isEmpty() && validate(token, user.getToken())) {
            return user;
        } else {
            throw new InvalidLoginException();
        }

    }

    private boolean validate(String token, String userToken) {
        try {
            String tokenTratado = token.replace("Bearer ", "");
            if (!tokenTratado.equals(userToken)) throw new InvalidTokenException();
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
