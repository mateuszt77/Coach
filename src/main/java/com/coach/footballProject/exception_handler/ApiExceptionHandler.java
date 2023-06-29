package com.coach.footballProject.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException are) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionDetails apiExceptionDetails = new ApiExceptionDetails
                (are.getMessage(), badRequest, ZonedDateTime.now(ZoneId.of("ZoneID")));
        return new ResponseEntity<>(apiExceptionDetails, badRequest);
    }
}