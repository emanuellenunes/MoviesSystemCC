package com.CC.Authentication.dto;

import com.CC.Authentication.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private String token;
    private String kind;

    public UserDTO(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public User toUser() {
        return new User(this.name, this.email, this.password, this.token);
    }
}
