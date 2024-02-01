package com.apps.securityapp.controller;

import com.apps.securityapp.dto.ResponseMessageDTO;
import com.apps.securityapp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/app")
public class AppController {
    private final AppService appService;

    @Autowired
    public AppController(AppService appService){
        this.appService = appService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/private")
    public ResponseEntity<ResponseMessageDTO> getPrivateContents(){
        return appService.getPrivateContents();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/public")
    public ResponseEntity<ResponseMessageDTO> getPublicContents(){
        return appService.getPublicContents();
    }
}
