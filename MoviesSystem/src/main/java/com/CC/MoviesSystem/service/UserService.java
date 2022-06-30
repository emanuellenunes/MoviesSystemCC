package com.CC.MoviesSystem.service;

import org.springframework.stereotype.Service;

import com.CC.MoviesSystem.entity.User;
import com.CC.MoviesSystem.enumeration.Profile;
import com.CC.MoviesSystem.exception.InvalidTokenException;
import com.CC.MoviesSystem.exception.UnauthorizedUserException;
import com.CC.MoviesSystem.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    //final int firstLevelScore = 20;
    //final int secondLevelScore = 100;
    //final int thirdLevelScore = 1000;
    final int firstLevelScore = 5;
    final int secondLevelScore = 10;
    final int thirdLevelScore = 15;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByToken(String Authorization) {
        String token = Authorization.replace("Bearer ", "");
        return userRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException());
    }

    public void validateProfile(User user, Profile profile){
        if (user.getProfile().ordinal() < profile.ordinal()) throw new UnauthorizedUserException();
    }

    public void updateUserScoreAndProfile(User user) {
        user.scoreIncrement();
        user.setProfile(upgradeProfile(user));
        userRepository.save(user);
    }

    public Profile upgradeProfile(User user) {
        Profile currProfile = user.getProfile();
        int score = user.getScore();
        if (currProfile == Profile.READER && score >= firstLevelScore && score < secondLevelScore) {
            currProfile = Profile.BASIC;
        } else if (currProfile == Profile.BASIC && score >= secondLevelScore && score < thirdLevelScore) {
            currProfile = Profile.ADVANCED;
        } else if (currProfile == Profile.ADVANCED && score >= thirdLevelScore) {
            currProfile = Profile.MODERATOR;
        }
        return currProfile;
    }
    
}
