package za.co.staffschedule.exception;

import za.co.staffschedule.service.ServiceException;

public class StaffException extends ServiceException {
    public StaffException(String message) {
        super(message);
    }

    public StaffException(String message, Exception exception) {
        super(message, exception);
    }
}
