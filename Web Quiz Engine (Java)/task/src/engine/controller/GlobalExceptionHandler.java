package engine.controller;

import engine.model.response.exceptions.InvalidValidationJsonException;
import engine.model.response.exceptions.InvalidIDException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<InvalidValidationJsonException> handleInvalidValidationJsonException(InvalidValidationJsonException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler
    public ResponseEntity<InvalidIDException> handleInvalidIDException(InvalidIDException exception) {
        return ResponseEntity.notFound().build();
    }
}
