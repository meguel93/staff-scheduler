package za.co.staffschedule.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDTO {
    private Long id;
    private String staff_id;
    private Date workDate;
    private int shiftHours;
}
