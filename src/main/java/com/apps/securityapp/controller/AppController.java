package com.apps.securityapp.controller;

import com.apps.securityapp.dto.SignUpDTO;
import com.apps.securityapp.model.User;
import com.apps.securityapp.service.AppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AppController {
    private final AppService service;

    @Autowired
    public AppController(AppService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public ResponseEntity<User> createUser(@Valid @RequestBody SignUpDTO signUpDTO){
        return service.createUser(signUpDTO);
    }
}
