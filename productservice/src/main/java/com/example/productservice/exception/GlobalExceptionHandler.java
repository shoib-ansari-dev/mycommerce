package com.example.productservice.exception;

import com.example.productservice.dto.ErrorResponse;
import com.example.productservice.filters.CorrelationIdFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourceNotFoundException(ResourceNotFoundException ex){

        String correlationId= (String) httpServletRequest.getAttribute(CorrelationIdFilter.CORRELATION_ID);
        ErrorResponse errorResponse= new ErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                httpServletRequest.getRequestURI(),
                System.currentTimeMillis(),
                correlationId
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
