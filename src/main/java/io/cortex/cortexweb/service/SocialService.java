package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.Social;
import org.springframework.stereotype.Service;

@Service
public interface SocialService {
    Iterable<Social> findAllUserFollowers(String email);

    Iterable<Social> findAllUserFollowing(String email);

    void unfollowUserFollowing(String userEmail, String followingEmail);
}
