package engine.model.response.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidIDException extends RuntimeException{
    public InvalidIDException(HttpStatus e){
        super(e.name());
    }
}
