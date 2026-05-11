package labair_api.exceptions;

public class ExistingShoeException extends RuntimeException {
    public ExistingShoeException() {
        super("Scarpa già esistente");
    }
}
