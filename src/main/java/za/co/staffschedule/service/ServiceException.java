package za.co.staffschedule.service;

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }
}
