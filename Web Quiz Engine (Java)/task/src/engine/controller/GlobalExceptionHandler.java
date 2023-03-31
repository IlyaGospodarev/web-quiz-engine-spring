package engine.controller;

import engine.model.response.exceptions.DuplicateEmailException;
import engine.model.response.exceptions.InvalidIDException;
import engine.model.response.exceptions.InvalidUserException;
import engine.model.response.exceptions.NotPermittedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DuplicateEmailException> handleDuplicateEmailException(DuplicateEmailException exception) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler
    public ResponseEntity<InvalidIDException> handleInvalidIDException(InvalidIDException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<InvalidUserException> handleInvalidUserException(InvalidUserException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<NotPermittedException> handleNotPermittedException(NotPermittedException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
