package com.CC.Authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.CC.Authentication.entity.User;
import com.CC.Authentication.service.UserAuthenticationService;
import com.CC.Authentication.dto.LoginDTO;

@RestController
public class UserAuthenticationController {
    
    private UserAuthenticationService userAuthenticationService;

    public UserAuthenticationController(UserAuthenticationService userAuthenticationService){
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO loginData, @RequestHeader String Authorization){
        User user = userAuthenticationService.authenticate(loginData, Authorization);
        return new ResponseEntity<String>(user.toDTO("Bearer ").getToken(), HttpStatus.ACCEPTED);
    }
}
