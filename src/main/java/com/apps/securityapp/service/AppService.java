package com.apps.securityapp.service;

import com.apps.securityapp.dto.LoginDTO;
import com.apps.securityapp.dto.ResponseMessageDTO;
import com.apps.securityapp.dto.SignUpDTO;
import com.apps.securityapp.model.User;
import org.springframework.http.ResponseEntity;

public interface AppService {
    ResponseEntity<ResponseMessageDTO> createUser(SignUpDTO signUpDTO);
    ResponseEntity<ResponseMessageDTO> login(LoginDTO loginDTO);
}
