package com.CC.MoviesSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.UserDTO;
import com.CC.MoviesSystem.service.UserService;

@RestController
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<UserDTO> searchUserByToken(@RequestHeader String Authorization) {
        UserDTO userDTO = userService.searchUserByToken(Authorization);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/user/turn-moderator")
    public ResponseEntity<UserDTO> turnModerator(@RequestHeader String Authorization, @RequestBody String email) {
        UserDTO userDTO = userService.turnModerator(email, Authorization);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    
}