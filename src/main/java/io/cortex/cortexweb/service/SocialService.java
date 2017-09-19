package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.Social;
import org.springframework.stereotype.Service;

@Service
public interface SocialService {
    Iterable<Social> findAllUserFollowers(String email);

    Iterable<Social> findAllUserFollowing(String email);

    void followUser(String followerEmail, int followerReputationScore, String followerUsername,
                    String followingEmail, int followingReputationScore, String followingUsername);

    void unfollowUser(String userEmail, String followingEmail);

    Iterable<Social> checkUserFollowing(String userEmail, String followingEmail);

    Iterable<Social> checkSocialTableSize();
}
