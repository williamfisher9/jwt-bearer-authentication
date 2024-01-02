package com.apps.securityapp.service;

import com.apps.securityapp.dao.UserRepository;
import com.apps.securityapp.dto.LoginDTO;
import com.apps.securityapp.dto.ResponseMessageDTO;
import com.apps.securityapp.dto.SignUpDTO;
import com.apps.securityapp.enums.ResponseType;
import com.apps.securityapp.exception.DuplicateEmailException;
import com.apps.securityapp.exception.DuplicateUsernameException;
import com.apps.securityapp.exception.EmptyRolesSetException;
import com.apps.securityapp.exception.InvalidRoleException;
import com.apps.securityapp.enums.ERole;
import com.apps.securityapp.model.Role;
import com.apps.securityapp.model.User;
import com.apps.securityapp.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AppServiceImpl implements AppService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Autowired
    public AppServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<ResponseMessageDTO> createUser(SignUpDTO dtoUser) {
        if(userRepository.findByUsername(dtoUser.getUsername()).isPresent()){
            throw new DuplicateUsernameException(dtoUser.getUsername() + ": user with the same username exists!");
        }

        if(userRepository.findByEmail(dtoUser.getEmail()).isPresent()){
            throw new DuplicateEmailException(dtoUser.getEmail() + ": user with the same email address exists!");
        }

        if(dtoUser.getRoles().isEmpty()){
            throw new EmptyRolesSetException("Roles set is empty!");
        }

        Set<Role> userRoles = new HashSet<>();
        for (String roleName:dtoUser.getRoles()){
            ERole eRole = ERole.getByName(roleName);
            if(eRole == null){
                    throw new InvalidRoleException(roleName + ": Invalid role was received!");
            }
            userRoles.add(new Role(eRole));
        }

        User user = new User();
        user.setEmail(dtoUser.getEmail());
        user.setUsername(dtoUser.getUsername());
        user.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
        user.setFullName(dtoUser.getEmail());
        user.setRoles(userRoles);

        User savedUser = userRepository.save(user);

        if(savedUser.getId() != 0){
            ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
            responseMessageDTO.setResponseType(ResponseType.SUCCESS);
            responseMessageDTO.setResponseDateTime(LocalDateTime.now());
            responseMessageDTO.setHttpStatusDescription(HttpStatus.CREATED.toString());
            responseMessageDTO.setHttpStatusCode(HttpStatus.CREATED.value());
            responseMessageDTO.setResponse(savedUser.toString());

            return new ResponseEntity<>(responseMessageDTO, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public ResponseEntity<ResponseMessageDTO> logUserIn(LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );

        String token = jwtUtils.generateJwtToken(authentication);

        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        responseMessageDTO.setResponseType(ResponseType.SUCCESS);
        responseMessageDTO.setResponseDateTime(LocalDateTime.now());
        responseMessageDTO.setHttpStatusDescription(HttpStatus.OK.getReasonPhrase());
        responseMessageDTO.setHttpStatusCode(HttpStatus.OK.value());
        responseMessageDTO.setResponse(token);

        return ResponseEntity.ok(responseMessageDTO);
    }
}
