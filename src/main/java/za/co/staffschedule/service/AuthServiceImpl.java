package za.co.staffschedule.service;

import org.springframework.stereotype.Service;
import za.co.staffschedule.exception.AuthException;
import za.co.staffschedule.request.AuthResponse;

@Service
public class AuthServiceImpl implements AuthService {
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
}
