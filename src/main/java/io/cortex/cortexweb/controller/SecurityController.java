package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.LoginResult;
import io.cortex.cortexweb.model.User;
import io.cortex.cortexweb.service.GoogleAuthService;
import io.cortex.cortexweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

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

            //manually sign in user
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),
                    "default_password");
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //unable to find user
        else {
            loginResult.setResult("FAILED");
        }

        return new ResponseEntity(loginResult, HttpStatus.OK);
    }
}
