package petonline.core.exceptions;

public class ApiException extends RuntimeException {

    private String message;

    public String getMessage() {
        return message;
    }

    public ApiException(String message) {
        super();
        this.message = message;
    }

    public ApiException() {
        super();
    }
}
