package com.CC.MoviesSystem.dto;

import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private Profile profile;
    
    public User toUser() {
        return new User(this.name, this.email, this.profile);
    }
}
