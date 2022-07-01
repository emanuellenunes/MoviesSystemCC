package com.CC.Authentication.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CC.Authentication.service.UserRegistrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import com.CC.Authentication.dto.UserDTO;
import com.CC.Authentication.entity.User;

@RestController
@Api(value = "UserRegistrationController", description = "Sign-up")
@RequestMapping("/sign-up")
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    private ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @ApiOperation(value = "Register a new user in the System and return its token")
    @PostMapping
    public ResponseEntity<String> registrate(@RequestBody UserDTO userRegistrationDTO) {
        User user = userRegistrationService.registrate(userRegistrationDTO.toUser());
        return new ResponseEntity<String>(user.toDTO("Bearer ").getToken(), HttpStatus.CREATED);
    }
}
