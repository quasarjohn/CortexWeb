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
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
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
    public void changePicture(String PICTURE_PATH, String email) {
        userRepository.changePicture(PICTURE_PATH, email);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
