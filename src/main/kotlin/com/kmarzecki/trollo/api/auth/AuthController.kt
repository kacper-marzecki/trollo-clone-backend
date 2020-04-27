package com.kmarzecki.trollo.api.auth

import com.kmarzecki.trollo.model.auth.LoginDto
import com.kmarzecki.trollo.model.auth.LoginResponse
import com.kmarzecki.trollo.model.auth.RegisterDto
import com.kmarzecki.trollo.model.auth.UserResponse
import com.kmarzecki.trollo.security.AuthenticationService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.security.Principal

/**
 * Controller for operations concerning User registration and login processes
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = ["*"], allowCredentials = "true", allowedHeaders = ["*"])
class AuthController(
        val authenticationService: AuthenticationService
) {

    /**
     * Login a user
     * @param request Request containing login information
     * @return logged user response
     */
    @PostMapping("/login")
    fun login(
            @RequestBody @Validated request: LoginRequest
    ): LoginResponse? {
        return authenticationService.login(LoginDto(request.username, request.password))
    }

    /**
     * Register a user
     * @param request Request containing registration information
     */
    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest) {
        authenticationService.register(RegisterDto(request.username, request.password, request.email))
    }

    /**
     * Get information about the logged-in user
     * @param principal Principal of the asking user
     * @return Use information
     */
    @GetMapping("/me")
    fun getMe(principal: Principal): UserResponse? {
        return authenticationService.getMe(principal)
    }
}