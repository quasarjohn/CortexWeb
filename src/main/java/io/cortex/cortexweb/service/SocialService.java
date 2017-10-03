package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.Social;
import org.springframework.stereotype.Service;

@Service
public interface SocialService {
    Iterable<Social> findAllUserFollowers(String email);

    Iterable<Social> findAllUserFollowing(String email);

    void followUser(String followerEmail, int followerReputationScore, String followerUsername, String followerImgUrl, String follower_PICTURE_PATH,
                    String followingEmail, int followingReputationScore, String followingUsername, String followingImgUrl, String following_PICTURE_PATH);

    void unfollowUser(String userEmail, String followingEmail);

    Iterable<Social> checkUserFollowing(String userEmail, String followingEmail);

    Iterable<Social> checkSocialTableSize();

    void changePictureFollowing(String PICTURE_PATH, String email);

    void changePictureFollower(String PICTURE_PATH, String email);

    void changeUsernameFollowing(String username, String email);

    void changeUsernameFollower(String username, String email);
}
