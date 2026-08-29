package labair_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingShoeException extends RuntimeException {
    public ExistingShoeException() {
        super("Scarpa già esistente");
    }
}
