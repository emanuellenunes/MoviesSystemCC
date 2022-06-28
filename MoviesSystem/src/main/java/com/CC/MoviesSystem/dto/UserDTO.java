package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.User;

import lombok.Data;

@Data
public class UserDTO extends User {
    private String kind;

    public UserDTO(String name, String email, String password, String token, String kind) {
        super(name, email, password, token);
        this.kind = kind;
    }
    
    public User toUser() {
        return new User(getName(), getEmail(), getPassword(), getToken());
    }
    
    public static UserDTO toDTO(User user, String kind) {
        return new UserDTO(user.getName(), user.getEmail(), user.getPassword(), user.getToken(), kind);
    }
}
