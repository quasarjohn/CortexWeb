package io.cortex.cortexweb.repository;

import io.cortex.cortexweb.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findUserByEmail(String email);
}