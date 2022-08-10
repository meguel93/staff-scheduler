package za.co.staffschedule.service;

import org.springframework.stereotype.Service;
import za.co.staffschedule.exception.ScheduleException;
import za.co.staffschedule.request.ScheduleRequest;
import za.co.staffschedule.request.ScheduleResponse;
import za.co.staffschedule.response.StaffResponse;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Override
    public ScheduleResponse createSchedule(ScheduleRequest request) throws ScheduleException {
        return null;
    }

    @Override
    public ScheduleResponse deleteSchedule(Long scheduleId) throws ScheduleException {
        return null;
    }

    @Override
    public StaffResponse getSchedules() throws ScheduleException {
        return null;
    }

    @Override
    public ScheduleResponse getScheduleById(Long scheduleId) throws ScheduleException {
        return null;
    }
}
