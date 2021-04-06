package com.dpiliotis.ride.hailing.app.controller;

import com.dpiliotis.ride.hailing.app.dto.ApiResponse;
import com.dpiliotis.ride.hailing.app.dto.ErrorDto;
import com.dpiliotis.ride.hailing.app.exception.ResourceNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiResponse<Void> notFound(ResourceNotFound e) {
        logger.error("Resource not found", e);

        ErrorDto error = new ErrorDto(e.getMessage());

        ApiResponse<Void> response = new ApiResponse<>();
        response.setErrors(Collections.singletonList(error));

        return response;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResponse<Void> generalError(Exception e) {
        logger.error("Something went wrong", e);

        ErrorDto error = new ErrorDto("Oops");
        ApiResponse<Void> response = new ApiResponse<>();
        response.setErrors(Collections.singletonList(error));

        return response;
    }
}
