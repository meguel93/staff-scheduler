package za.co.staffschedule.exception;

public class AuthException extends Exception {
    public AuthException(String message) {
        super(message);
    }

    public AuthException(String message, Exception exception) {
        super(message, exception);
    }
}
