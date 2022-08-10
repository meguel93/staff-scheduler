package za.co.staffschedule.service;

import za.co.staffschedule.exception.AuthException;
import za.co.staffschedule.request.AuthResponse;

public interface AuthService {

    AuthResponse login(String username, String password) throws AuthException;

    AuthResponse forgotPassword(String email);

    AuthResponse resetPassword(AuthResponse dto);
}
