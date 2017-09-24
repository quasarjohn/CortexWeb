package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findOne(email);
    }

    @Override
    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(String username, String bio, String email) {
        userRepository.updateUser(username, bio, email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
