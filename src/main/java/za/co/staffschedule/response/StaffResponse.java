package za.co.staffschedule.response;

import lombok.Data;
import za.co.staffschedule.dto.StaffDTO;

import java.util.List;

@Data
public class StaffResponse {
    List<StaffDTO> staffList;
    StaffDTO staff;

    public StaffResponse(StaffDTO staff) {
        this.staff = staff;
    }

    public StaffResponse(List<StaffDTO> staffList) {
        this.staffList = staffList;
    }
}
