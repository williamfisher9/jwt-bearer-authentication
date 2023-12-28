package com.apps.securityapp.controller;

import com.apps.securityapp.dto.LoginDTO;
import com.apps.securityapp.dto.ResponseMessageDTO;
import com.apps.securityapp.dto.SignUpDTO;
import com.apps.securityapp.model.User;
import com.apps.securityapp.service.AppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/app")
public class AppController {
    private final AppService service;

    @Autowired
    public AppController(AppService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/secure")
    public ResponseEntity<ResponseMessageDTO> getSecureContents(){
        return new ResponseEntity<>(new ResponseMessageDTO("Secure contents"), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/public")
    public ResponseEntity<ResponseMessageDTO> getPublicContents(){
        return new ResponseEntity<>(new ResponseMessageDTO("Public contents"), HttpStatus.OK);
    }
}
