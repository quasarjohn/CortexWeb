package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.LoginResult;
import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.service.GoogleAuthService;
import io.cortex.cortexweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/test")
public class SecurityController {

    @Autowired
    GoogleAuthService googleAuthService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> manualLogin(@RequestParam(name = "token") String token) {

        LoginResult loginResult = new LoginResult();
        User user = null;

        try {
            user = googleAuthService.authenticate(token);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //means the security token is valid
        if (user.getEmail() != null) {
            //first check if user has already signed up
            if (userService.findUserByEmail(user.getEmail()) == null) {
                System.out.println("SAVING USER");
                userService.saveUser(user);
            } else {
                System.out.println(user.getEmail() + " already exists!!!");
            }

            loginResult.setResult("SUCCESS");
            //sign up the user
        }
        //unable to find user
        else {
            loginResult.setResult("FAILED");
        }

        return new ResponseEntity(loginResult, HttpStatus.OK);
    }
}
