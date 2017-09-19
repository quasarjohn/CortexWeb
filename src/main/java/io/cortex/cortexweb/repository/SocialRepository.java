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
    @Query(value = "delete from social where follower_email = ?1 and following_email = ?2", nativeQuery = true)
    void unfollowUserFollowing(String userEmail, String followingEmail);
}
