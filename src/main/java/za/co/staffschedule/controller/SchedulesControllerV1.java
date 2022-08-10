package za.co.staffschedule.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.staffschedule.exception.ScheduleException;
import za.co.staffschedule.request.ScheduleRequest;
import za.co.staffschedule.request.ScheduleResponse;
import za.co.staffschedule.response.StaffResponse;
import za.co.staffschedule.service.ScheduleService;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "v1/schedules", produces = "application/json")
@AllArgsConstructor
public class SchedulesControllerV1 extends BaseController {

    private final ScheduleService scheduleService;

    @RolesAllowed({"ROLE_ADMIN"})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ScheduleResponse> createSchedule(ScheduleRequest request) throws ScheduleException {
        ScheduleResponse response = scheduleService.createSchedule(request);
        return createdResponse("/", response.getSchedule().getId(), response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable("scheduleId") Long scheduleId) throws ScheduleException {
        ScheduleResponse response = scheduleService.getScheduleById(scheduleId);
        return okResponse(response);
    }


    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponse> deleteSchedule(@PathVariable("scheduleId") Long scheduleId) throws ScheduleException {
        ScheduleResponse response = scheduleService.deleteSchedule(scheduleId);
        return okResponse(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<StaffResponse> getSchedules() throws ScheduleException {
        StaffResponse response = scheduleService.getSchedules();
        return okResponse(response);
    }

}










