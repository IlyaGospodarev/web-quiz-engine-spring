package engine.model.response.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(HttpStatus e){
        super(e.name());
    }
}
