package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserByEmail(String email);

    //User save(User user);

    //Iterable<User> list
    /*private UserRepository userRepository;

    //@Autowired
    //public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //public User findUserByEmail(String email) {
        //return userRepository.findUserByEmail(email);
    }*/
}
