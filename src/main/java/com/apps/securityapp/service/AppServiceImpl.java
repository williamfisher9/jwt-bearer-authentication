package com.apps.securityapp.service;

import com.apps.securityapp.dao.UserRepository;
import com.apps.securityapp.dto.SignUpDTO;
import com.apps.securityapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppServiceImpl implements AppService{
    private final UserRepository userRepository;

    @Autowired
    public AppServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<User> createUser(SignUpDTO dtoUser) {
        User user = new User();
        user.setEmail(dtoUser.getEmail());
        user.setUsername(dtoUser.getUsername());
        user.setPassword(dtoUser.getPassword());
        user.setFullName(dtoUser.getEmail());

        User savedUser = userRepository.save(user);

        if(savedUser.getId() != 0){
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
