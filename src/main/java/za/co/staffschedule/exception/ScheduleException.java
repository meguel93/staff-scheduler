package za.co.staffschedule.exception;

import za.co.staffschedule.service.ServiceException;

public class ScheduleException extends ServiceException {
    public ScheduleException(String message) {
        super(message);
    }

    public ScheduleException(String message, Exception exception) {
        super(message, exception);
    }
}
