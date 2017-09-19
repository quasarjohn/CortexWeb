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
    public void unfollowUserFollowing(String userEmail, String followingEmail) {
        socialRepository.unfollowUserFollowing(userEmail, followingEmail);
    }
}
