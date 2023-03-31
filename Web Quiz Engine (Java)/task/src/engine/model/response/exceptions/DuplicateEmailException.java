package engine.model.response.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends RuntimeException{
    public DuplicateEmailException(HttpStatus e) {
        super(e.name());
    }
}
