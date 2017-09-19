package io.cortex.cortexweb.repository;

import io.cortex.cortexweb.model.Social;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SocialRepository extends CrudRepository<Social, String> {
    @Query(value = "select * from social where following_email = ?1", nativeQuery = true)
    Iterable<Social> findAllUserFollowers(String email);

    @Query(value = "select * from social where follower_email = ?1", nativeQuery = true)
    Iterable<Social> findAllUserFollowing(String email);

    @Modifying
    @Transactional
    @Query(value = "insert into social (follower_email, follower_reputation_score, follower_username, following_email, following_reputation_score, following_username) " +
            "values (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void followUser(String followerEmail, int followerReputationScore, String followerUsername,
                    String followingEmail, int followingReputationScore, String followingUsername);

    @Modifying
    @Transactional
    @Query(value = "delete from social where follower_email = ?1 and following_email = ?2", nativeQuery = true)
    void unfollowUser(String userEmail, String followingEmail);

    @Query(value = "select * from social where follower_email = ?1 and following_email = ?2", nativeQuery = true)
    Iterable<Social> checkUserFollowing(String userEmail, String followingEmail);

    @Query(value = "select * from social", nativeQuery = true)
    Iterable<Social> checkSocialTableSize();
}
