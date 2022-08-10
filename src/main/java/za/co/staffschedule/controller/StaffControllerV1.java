package za.co.staffschedule.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.staffschedule.exception.StaffException;
import za.co.staffschedule.request.StaffRequestRequest;
import za.co.staffschedule.response.StaffResponse;
import za.co.staffschedule.service.StaffService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "v1/staff", produces = "application/json")
@AllArgsConstructor
public class StaffControllerV1 extends BaseController {
    private final StaffService staffService;

    //@RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping(consumes = "application/json")
    public ResponseEntity<StaffResponse> register(@RequestBody StaffRequestRequest request) throws StaffException {
        StaffResponse response = staffService.register(request);
        return createdResponse("/", response.getStaff().getId(), response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping("/{userId}")
    public ResponseEntity<StaffResponse> getUserDetails(@PathVariable("userId") Long userId) throws StaffException {
        StaffResponse response = staffService.getUserDetails(userId);
        return okResponse(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<StaffResponse> getUsers() throws StaffException {
        StaffResponse response = staffService.getUsers();
        return okResponse(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @PatchMapping
    public ResponseEntity<StaffResponse> updateUser(StaffRequestRequest request) throws StaffException {
        StaffResponse response = staffService.update(request);
        return okResponse(response);
    }

    @RolesAllowed({"ROLE_ADMIN"})
    @DeleteMapping("delete/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) throws StaffException {
        staffService.delete(userId);
    }

}
