package engine.model.response.exceptions;

import org.springframework.http.HttpStatus;

public class NotPermittedException extends RuntimeException{
    public NotPermittedException(HttpStatus e) {
        super(e.name());
    }
}
