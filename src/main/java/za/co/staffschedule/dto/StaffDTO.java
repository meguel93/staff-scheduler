package za.co.staffschedule.dto;

import lombok.Data;

import java.util.List;

@Data
public class StaffDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<RoleDTO> roles;
}
