package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.ReturnableUser;
import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.repository.UserRepository;
import io.cortex.cortexweb.security.IAuthenticationManager;
import io.cortex.cortexweb.service.UserService;
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

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private IAuthenticationManager authenticationManager;

    @GetMapping("/user-info")
    public ReturnableUser getUserInfo() {
        ReturnableUser returnableUser = new ReturnableUser();

        User user = userService.findUserByEmail(authenticationManager.getCurrentUser());

        if(user != null) {
            returnableUser.setEmail(user.getEmail());
            returnableUser.setFirst_name(user.getFirstName());
            returnableUser.setLast_name(user.getLastName());
        }

        return returnableUser;
    }
}
