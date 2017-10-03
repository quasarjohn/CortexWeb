package io.cortex.cortexweb.service;

import io.cortex.cortexweb.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserByEmail(String email);

    User findUserByUsername(String username);

    Iterable<User> findAllUsers();

    void updateUser(String username, String bio, String email);

    void changePicture(String PICTURE_PATH, String email);

    void saveUser(User user);

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
