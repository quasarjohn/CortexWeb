package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.Social;
import io.cortex.cortexweb.repository.SocialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialServiceImpl implements SocialService {
    private SocialRepository socialRepository;

    @Autowired
    public void setSocialRepository(SocialRepository socialRepository) {
        this.socialRepository = socialRepository;
    }

    @Override
    public Iterable<Social> findAllUserFollowers(String email) {
        return socialRepository.findAllUserFollowers(email);
    }

    @Override
    public Iterable<Social> findAllUserFollowing(String email) {
        return socialRepository.findAllUserFollowing(email);
    }

    @Override
    public void followUser(String followerEmail, int followerReputationScore, String followerUsername, String followingEmail, int followingReputationScore, String followingUsername) {
        socialRepository.followUser(followerEmail, followerReputationScore, followerUsername, followingEmail, followingReputationScore, followingUsername);
    }

    @Override
    public void unfollowUser(String userEmail, String followingEmail) {
        socialRepository.unfollowUser(userEmail, followingEmail);
    }

    @Override
    public Iterable<Social> checkUserFollowing(String userEmail, String followingEmail) {
       return socialRepository.checkUserFollowing(userEmail, followingEmail);
    }

    @Override
    public Iterable<Social> checkSocialTableSize() {
        return socialRepository.checkSocialTableSize();
    }
}
