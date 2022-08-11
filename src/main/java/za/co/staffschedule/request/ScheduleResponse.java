package za.co.staffschedule.request;

import lombok.Data;
import za.co.staffschedule.dto.ScheduleDTO;

import java.util.List;

@Data
public class ScheduleResponse {
    ScheduleDTO schedule;
    List<ScheduleDTO> scheduleList;

    public ScheduleResponse(ScheduleDTO schedule) {
        this.schedule = schedule;
    }

    public ScheduleResponse(List<ScheduleDTO> scheduleList) {
        this.scheduleList = scheduleList;
    }
}
