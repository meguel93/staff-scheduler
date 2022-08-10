package za.co.staffschedule.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.staffschedule.exception.AuthException;
import za.co.staffschedule.request.AuthResponse;
import za.co.staffschedule.response.ApiErrorResponse;
import za.co.staffschedule.service.AuthService;

@RestController
@RequestMapping(value = "v1/auth", produces = "application/json")
@AllArgsConstructor
public class AuthControllerV1 extends BaseController {
    private final AuthService authService;

    @PostMapping(consumes = "application/x-www-form-urlencoded")
    @Operation(description = "Login",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Login successful",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Not authorized",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<AuthResponse> login(String username, String password) throws AuthException {
        AuthResponse response = authService.login(username, password);
        return okResponse(response);
    }

    @PutMapping(value = "forgot-password/{email}")
    @Operation(description = "Forgot password",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Forgot password successful",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "404", description = "No user found that forgot password",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<AuthResponse> forgotPassword(@PathVariable(value = "email") String email) throws AuthException {
        AuthResponse response = authService.forgotPassword(email);
        return okResponse(response);
    }

    @PutMapping(value = "reset-password")
    @Operation(description = "Reset password",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "Password reset successful",
                            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
                    @ApiResponse(responseCode = "404", description = "No user found to reset password",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
            })
    public ResponseEntity<AuthResponse> resetPassword(@RequestBody AuthResponse dto) throws AuthException {
        AuthResponse response = authService.resetPassword(dto);
        return okResponse(response);
    }

}









