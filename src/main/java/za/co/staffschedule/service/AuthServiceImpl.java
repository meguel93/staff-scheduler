package za.co.staffschedule.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.co.staffschedule.exception.AuthException;
import za.co.staffschedule.model.Staff;
import za.co.staffschedule.repository.StaffRepository;
import za.co.staffschedule.request.AuthResponse;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService, UserDetailsService  {
    private final StaffRepository staffRepository;
    @Override
    public AuthResponse login(String username, String password) throws AuthException {
        return null;
    }

    @Override
    public AuthResponse forgotPassword(String email) {
        return null;
    }

    @Override
    public AuthResponse resetPassword(AuthResponse dto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Staff staff = staffRepository.findByUserName(username);
        Set<GrantedAuthority> grantedAuthorities = staff.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toCollection(HashSet::new));
        return new org.springframework.security.core.userdetails.User(staff.getUsername(), staff.getPassword(), grantedAuthorities);
    }
}
