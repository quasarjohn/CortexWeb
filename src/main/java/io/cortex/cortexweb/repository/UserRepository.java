package io.cortex.cortexweb.repository;

import io.cortex.cortexweb.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, String> {
    //User findUserByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "update users set username = ?1, bio = ?2 where email = ?3", nativeQuery = true)
    void updateUser(String username, String bio, String email);

    User findUserByUsername(String email);
}