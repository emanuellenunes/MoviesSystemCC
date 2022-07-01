package com.CC.MoviesSystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.CC.MoviesSystem.dto.UserDTO;
import com.CC.MoviesSystem.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "UserController", description = "User-related operations")
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Show User info")
    @GetMapping
    public ResponseEntity<UserDTO> searchUserByToken(@RequestHeader String Authorization) {
        UserDTO userDTO = userService.searchUserByToken(Authorization);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }

    @ApiOperation(value = "Turn another user a MODERATOR by email")
    @PostMapping("/turn-moderator")
    public ResponseEntity<UserDTO> turnModerator(@RequestHeader String Authorization, @RequestBody String email) {
        UserDTO userDTO = userService.turnModerator(email, Authorization);
        return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
    }
    
}