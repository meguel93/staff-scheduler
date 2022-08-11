package za.co.staffschedule.service;

import za.co.staffschedule.exception.ScheduleException;
import za.co.staffschedule.model.Schedule;
import za.co.staffschedule.request.ScheduleRequest;
import za.co.staffschedule.request.ScheduleResponse;
import za.co.staffschedule.response.StaffResponse;

import java.util.List;

public interface ScheduleService {


    ScheduleResponse createSchedule(ScheduleRequest request) throws ScheduleException;

    void deleteSchedule(Long scheduleId) throws ScheduleException;

    StaffResponse getSchedules() throws ScheduleException;

    ScheduleResponse getScheduleById(Long scheduleId) throws ScheduleException;

    List<Schedule> getAllSchedules(String username);

    ScheduleResponse updateSchedule(ScheduleRequest request) throws ScheduleException;
}
