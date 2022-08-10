package za.co.staffschedule.request;

import lombok.Data;
import za.co.staffschedule.dto.StaffDTO;

@Data
public class StaffRequestRequest {
    private StaffDTO staffDetails;
}
