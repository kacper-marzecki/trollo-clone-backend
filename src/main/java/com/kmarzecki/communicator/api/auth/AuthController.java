package com.kmarzecki.communicator.api.auth;

import com.kmarzecki.communicator.model.Language;
import com.kmarzecki.communicator.model.auth.LoginDto;
import com.kmarzecki.communicator.model.auth.LoginResponse;
import com.kmarzecki.communicator.model.auth.RegisterDto;
import com.kmarzecki.communicator.model.auth.UserResponse;
import com.kmarzecki.communicator.security.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


/**
 *  Controller for operations concerning User registration and login processes
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"*"},allowCredentials = "true", allowedHeaders = "*")
@AllArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    /**
     * Login a user
     * @param request Request containing login information
     * @return logged user response
     */
    @PostMapping("/login")
    public LoginResponse login(
            @RequestBody @Validated LoginRequest request
    ) {
        return authenticationService.login(new LoginDto(request.getUsername(), request.getPassword()));
    }

    /**
     * Register a user
     * @param request Request containing registration information
     * @param language user language
     */
    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest request) {
        authenticationService.register(new RegisterDto(request.getUsername(), request.getPassword(), request.getEmail()));
    }

    /**
     * Get information about the logged-in user
     * @param principal Principal of the asking user
     * @return Use information
     */
    @GetMapping("/me")
    public UserResponse getMe(Principal principal) {
        return authenticationService.getMe(principal);
    }
}
