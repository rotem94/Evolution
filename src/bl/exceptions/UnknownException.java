package bl.exceptions;

public class UnknownException extends RuntimeException{

    public UnknownException(String errorMessage) {
        super(errorMessage);
    }
}
