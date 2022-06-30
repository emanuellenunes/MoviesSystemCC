package com.CC.MoviesSystem.service;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.exception.InvalidTokenException;
import com.CC.MoviesSystem.exception.UnauthorizedUser;
import com.CC.MoviesSystem.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByToken(String Authorization) {
        String token = Authorization.replace("Bearer ", "");
        return userRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException());
    }

    public void validateProfile(User user, Profile profile){
        if (user.getProfile().ordinal() < profile.ordinal()) throw new UnauthorizedUser();
    }

    public void updateUserScoreAndProfile(User user) {
        user.scoreIncrement();
        user.setProfile(upgradeProfile(user));
        userRepository.save(user);
    }

    public Profile upgradeProfile(User user) {
        Profile currProfile = user.getProfile();
        int score = user.getScore();
        if (currProfile == Profile.READER && score >= 20 && score < 100) {
            currProfile = Profile.BASIC;
        } else if (currProfile == Profile.BASIC && score >= 100 && score < 1000) {
            currProfile = Profile.ADVANCED;
        } else if (currProfile == Profile.ADVANCED && score >= 1000) {
            currProfile = Profile.MODERATOR;
        }
        return currProfile;
    }
    
}
