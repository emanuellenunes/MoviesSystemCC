package com.CC.Authentication.dto;

import com.CC.Authentication.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private String token;
    private String kind;
    
    public User toUser() {
        return new User(this.name, this.email, this.password, this.token);
    }
}
