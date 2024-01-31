package com.apps.securityapp.controller;

import com.apps.securityapp.dto.ResponseMessageDTO;
import com.apps.securityapp.enums.ResponseType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/app")
public class AppController {
    @RequestMapping(method = RequestMethod.GET, path = "/private")
    public ResponseEntity<ResponseMessageDTO> getSecureContents(){
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        responseMessageDTO.setResponseType(ResponseType.SUCCESS);
        responseMessageDTO.setResponseDateTime(LocalDateTime.now());
        responseMessageDTO.setHttpStatusDescription(HttpStatus.OK.toString());
        responseMessageDTO.setHttpStatusCode(HttpStatus.OK.value());
        responseMessageDTO.setResponse("Private contents...");
        return new ResponseEntity<>(responseMessageDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/public")
    public ResponseEntity<ResponseMessageDTO> getPublicContents(){
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        responseMessageDTO.setResponseType(ResponseType.SUCCESS);
        responseMessageDTO.setResponseDateTime(LocalDateTime.now());
        responseMessageDTO.setHttpStatusDescription(HttpStatus.OK.toString());
        responseMessageDTO.setHttpStatusCode(HttpStatus.OK.value());
        responseMessageDTO.setResponse("Public contents...");
        return new ResponseEntity<>(responseMessageDTO, HttpStatus.OK);
    }
}
