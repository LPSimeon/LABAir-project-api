package labair_api.exceptions;

public class ExistingUserException extends RuntimeException {
    public ExistingUserException() {
        super("utente già esistente");
    }
}
