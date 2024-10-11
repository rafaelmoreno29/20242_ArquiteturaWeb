package com.example.projetoescola.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.projetoescola.exceptions.RegraNegocioException;
import com.example.projetoescola.dtos.ApiErrorDTO;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {
    @ExceptionHandler(RegraNegocioException.class)
    public ApiErrorDTO handleRegraNegocioException(
            RegraNegocioException ex) {
        String msg = ex.getMessage();
        return new ApiErrorDTO(msg);
    }
}