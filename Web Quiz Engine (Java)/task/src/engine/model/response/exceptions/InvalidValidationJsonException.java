package engine.model.response.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidValidationJsonException extends RuntimeException{
    public InvalidValidationJsonException(HttpStatus e) {
        super(e.name());
    }
}
