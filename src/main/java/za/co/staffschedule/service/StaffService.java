package za.co.staffschedule.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import za.co.staffschedule.exception.StaffException;
import za.co.staffschedule.request.StaffRequestRequest;
import za.co.staffschedule.response.StaffResponse;

@Service
public interface StaffService {

    StaffResponse register(StaffRequestRequest userRegistrationRequest) throws StaffException;

    StaffResponse update(StaffRequestRequest userRegistrationRequest) throws StaffException;

    void delete(Long id) throws StaffException;

    StaffResponse getUserDetails(Long id) throws StaffException;

    StaffResponse getUsers() throws StaffException;
}
