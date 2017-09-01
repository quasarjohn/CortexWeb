package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.ReturnableUser;
import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.repository.UserRepository;
import io.cortex.cortexweb.security.IAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {

    /*
    This class allows us to access user information from javascript file using ajax request
     */

    @Autowired
    private IAuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user-info")
    public ReturnableUser getUserInfo() {
        User user = userRepository.findUserByEmail(authenticationManager.getCurrentUser());

        ReturnableUser returnableUser = new ReturnableUser();
        returnableUser.setEmail(user.getEmail());
        returnableUser.setFirst_name(user.getFirstName());
        returnableUser.setLast_name(user.getLastName());

        return returnableUser;
    }
}
