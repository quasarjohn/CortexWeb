package io.cortex.cortexweb.controller;

import io.cortex.cortexweb.model.LoginResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class SecurityController {

    @PostMapping("/login")
    public ResponseEntity<?> manualLogin(@RequestParam(name = "token") String token) {
        //TODO get user's info using the security token to sign him up for cortex
        System.out.println(token);

        return new ResponseEntity(new LoginResult(), HttpStatus.OK);
    }
}
