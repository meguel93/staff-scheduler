package za.co.staffschedule.request;

import lombok.Data;
import za.co.staffschedule.dto.ScheduleDTO;

@Data
public class ScheduleResponse {
    private ScheduleDTO schedule;
    private Long userId;
}
