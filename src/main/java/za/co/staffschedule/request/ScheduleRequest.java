package za.co.staffschedule.request;

import lombok.Data;
import za.co.staffschedule.dto.ScheduleDTO;

import java.time.LocalDateTime;

@Data
public class ScheduleRequest {
    private ScheduleDTO scheduleDetails;

}
