package com.CC.Authentication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CC.Authentication.entity.User;
import com.CC.Authentication.service.UserAuthenticationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.CC.Authentication.dto.LoginDTO;

@RestController
@Api(value = "UserAuthenticationController", description = "Login")
@RequestMapping("/login")
public class UserAuthenticationController {
    
    private UserAuthenticationService userAuthenticationService;

    public UserAuthenticationController(UserAuthenticationService userAuthenticationService){
        this.userAuthenticationService = userAuthenticationService;
    }

    @ApiOperation(value = "Log the user in the System and return its token")
    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody LoginDTO loginData, @RequestHeader String Authorization) {
        try {
            User user = userAuthenticationService.authenticate(loginData, Authorization);
            return new ResponseEntity<String>(user.toDTO("Bearer ").getToken(), HttpStatus.ACCEPTED);
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
