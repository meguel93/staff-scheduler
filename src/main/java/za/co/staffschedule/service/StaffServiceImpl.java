package za.co.staffschedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.staffschedule.dto.RoleDTO;
import za.co.staffschedule.dto.StaffDTO;
import za.co.staffschedule.exception.ConflictException;
import za.co.staffschedule.exception.NotFoundException;
import za.co.staffschedule.exception.StaffException;
import za.co.staffschedule.mapper.StaffMapper;
import za.co.staffschedule.model.Role;
import za.co.staffschedule.model.Staff;
import za.co.staffschedule.repository.RoleRepository;
import za.co.staffschedule.repository.StaffRepository;
import za.co.staffschedule.request.StaffRequestRequest;
import za.co.staffschedule.response.StaffResponse;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StaffServiceImpl implements UserDetailsService, StaffService {
    private final StaffRepository staffRepository;
    private final RoleRepository roleRepository;

    private final StaffMapper staffMapper;

    @Override
    public StaffResponse register(StaffRequestRequest request) throws StaffException {
        StaffDTO userDetails = request.getStaffDetails();
        validateUser(userDetails);
        Staff newStaff = staffMapper.convertToDTO(userDetails);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newStaff.setPassword(encoder.encode(newStaff.getPassword())); //Encode password

        addUserRolesIfPossible(userDetails, newStaff);
        newStaff = staffRepository.save(newStaff);
        log.info(String.format("User has been created successfully %s", newStaff.getId()));
        return getUserResponse(newStaff);
    }

    private void addUserRolesIfPossible(StaffDTO userDetails, Staff newStaff) {
        List<Long> ids = userDetails.getRoles().stream().map(RoleDTO::getId).collect(Collectors.toList());
        List<Role> rolesToLink = roleRepository.findAllById(ids);
        if (CollectionUtils.isEmpty(rolesToLink)) return;
        newStaff.setRoles(rolesToLink);
    }

    private StaffResponse getUserResponse(Staff staff) {
        return new StaffResponse(staffMapper.convert(staff));
    }

    @Override
    public StaffResponse update(StaffRequestRequest request) throws StaffException {
        StaffDTO userDetails = request.getStaffDetails();
        fetchUserById(userDetails.getId());
        Staff updated = staffMapper.convertToDTO(userDetails);
        updated = staffRepository.save(updated);
        return getUserResponse(updated);
    }

    @Override
    public void delete(Long id) throws StaffException {
        Staff staff = fetchUserById(id);
        staffRepository.delete(staff);
    }

    @Override
    public StaffResponse getUserDetails(Long id) throws StaffException {
        Staff staff = fetchUserById(id);
        return new StaffResponse(staffMapper.convert(staff));
    }

    @Override
    public StaffResponse getUsers() throws StaffException {
        List<Staff> staff = staffRepository.findAll();
        List<StaffDTO> convertedUsers = staff.stream().map(staffMapper::convert).collect(Collectors.toList());
        return new StaffResponse(convertedUsers);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByUserName(username);
        Set<GrantedAuthority> grantedAuthorities = staff.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toCollection(HashSet::new));
        return new org.springframework.security.core.userdetails.User(staff.getUserName(), staff.getPassword(), grantedAuthorities);
    }

    private Staff fetchUserById(Long id) {
        Optional<Staff> userOptional = staffRepository.findById(id);
        if (!userOptional.isPresent()) throw new NotFoundException("User not found");
        return userOptional.get();
    }

    private void validateUser(StaffDTO userDetails) {
        Staff staff = staffRepository.findByEmail(userDetails.getEmail());
        if (Objects.nonNull(staff)) throw new ConflictException("User already exist");
    }

}
