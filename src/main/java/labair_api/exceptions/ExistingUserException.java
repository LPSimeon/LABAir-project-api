package labair_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ExistingUserException extends RuntimeException {
    public ExistingUserException() {
        super("utente già esistente");
    }
}
