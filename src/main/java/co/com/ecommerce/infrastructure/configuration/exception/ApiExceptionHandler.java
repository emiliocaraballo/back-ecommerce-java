package co.com.ecommerce.infrastructure.configuration.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import co.com.ecommerce.application.exception.CustomException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getCode(),
                e.getDescription(),
                e.getTitle(),
                e.getStatusCode(),
                "");
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getStatusCode()));
    }

    @ExceptionHandler(CustomInfrastructureException.class)
    public ResponseEntity<ErrorResponse> handleCustomInfrastructureException(CustomInfrastructureException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getCode(),
                e.getDescription(),
                e.getTitle(),
                e.getStatusCode(),
                "");
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getStatusCode()));
    }
}
