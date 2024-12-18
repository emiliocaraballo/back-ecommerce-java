package co.com.ecommerce.infrastructure.configuration.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import co.com.ecommerce.application.exception.CustomException;

@Order(1) // Esta clase tiene la mayor prioridad
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Maneja excepciones de tipo CustomException.
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        logException(e);
        ErrorResponse errorResponse = new ErrorResponse(
                e.getCode(), 
                e.getDescription(), 
                e.getTitle(), 
                e.getStatusCode(), 
                ""
        );
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

    /**
     * Maneja todas las excepciones genéricas de tipo Exception.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        logException(e);
        ErrorResponse errorResponse = new ErrorResponse(
                "INTERNAL_SERVER_ERROR", 
                "Se produjo un error inesperado", 
                "Lo sentimos", 
                HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                ""
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Registra la excepción en los logs.
     */
    private void logException(Exception e) {
        logger.error("Error en la aplicación: ", e);
    }
}
