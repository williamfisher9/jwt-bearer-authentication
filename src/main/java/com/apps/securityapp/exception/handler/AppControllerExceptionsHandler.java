package com.apps.securityapp.exception.handler;

import com.apps.securityapp.dto.ResponseMessageDTO;
import com.apps.securityapp.enums.ResponseType;
import com.apps.securityapp.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppControllerExceptionsHandler {
    @ExceptionHandler({DuplicateEmailException.class, DuplicateUsernameException.class})
    public ResponseEntity<ResponseMessageDTO> handleDuplicateRecordException(DuplicateRecordException exc){
        return new ResponseEntity<>(prepareResponseMessageDTO(exc.getExceptionMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseMessageDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(prepareResponseMessageDTO(errors.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EmptyRolesSetException.class, InvalidRoleException.class})
    public ResponseEntity<ResponseMessageDTO> handleRolesException(RoleException exc){
        return new ResponseEntity<>(prepareResponseMessageDTO(exc.getExceptionMessage()), HttpStatus.BAD_REQUEST);
    }

    public ResponseMessageDTO prepareResponseMessageDTO(String message){
        ResponseMessageDTO responseMessageDTO = new ResponseMessageDTO();
        responseMessageDTO.setResponseType(ResponseType.ERROR);
        responseMessageDTO.setResponseDateTime(LocalDateTime.now());
        responseMessageDTO.setHttpStatusDescription(HttpStatus.BAD_REQUEST.getReasonPhrase());
        responseMessageDTO.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        responseMessageDTO.setResponse(message);

        return responseMessageDTO;
    }
}
