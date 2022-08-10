package za.co.staffschedule.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScheduleRequest {
    private Long userId;
    private LocalDateTime startTime;
    private LocalDateTime endDate;
}
