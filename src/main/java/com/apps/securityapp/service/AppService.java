package com.apps.securityapp.service;

import com.apps.securityapp.dto.SignUpDTO;
import com.apps.securityapp.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AppService {
    ResponseEntity<User> createUser(SignUpDTO user);
}
