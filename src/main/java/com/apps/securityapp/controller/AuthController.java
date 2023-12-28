package com.apps.securityapp.controller;

import com.apps.securityapp.dto.LoginDTO;
import com.apps.securityapp.dto.ResponseMessageDTO;
import com.apps.securityapp.dto.SignUpDTO;
import com.apps.securityapp.service.AppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AppService service;

    @Autowired
    public AuthController(AppService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/signup")
    public ResponseEntity<ResponseMessageDTO> createUser(@Valid @RequestBody SignUpDTO signUpDTO){
        return service.createUser(signUpDTO);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/login")
    public ResponseEntity<ResponseMessageDTO> login(@Valid @RequestBody LoginDTO loginDTO){
        return service.logUserIn(loginDTO);
    }
}
