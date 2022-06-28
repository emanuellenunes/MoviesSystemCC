package com.CC.MoviesSystem.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.service.UserRegistrationService;
import com.CC.MoviesSystem.dto.UserDTO;
import com.CC.MoviesSystem.entity.User;

@RestController
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    private ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/user")
    public ResponseEntity<String> registrate(@RequestBody UserDTO userRegistrationDTO) {
        User user = userRegistrationService.registrate(userRegistrationDTO.toUser());
        return new ResponseEntity<String>(UserDTO.toDTO(user, "Bearer ").getToken(), HttpStatus.CREATED);
    }
}
