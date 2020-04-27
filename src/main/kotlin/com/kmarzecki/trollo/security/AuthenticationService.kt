package com.kmarzecki.trollo.security

import com.kmarzecki.trollo.model.auth.LoginDto
import com.kmarzecki.trollo.model.auth.LoginResponse
import com.kmarzecki.trollo.model.auth.RegisterDto
import com.kmarzecki.trollo.model.auth.UserResponse
import java.security.Principal

/**
 * Authentication service
 */
interface AuthenticationService {
    /**
     * Login user
     * @param dto dto containing login information
     * @return logged user response
     */
    fun login(dto: LoginDto): LoginResponse

    /**
     * Register user
     * @param registerDto dto containing registration information
     */
    fun register(registerDto: RegisterDto)

    /**
     * Get user information
     * @param principal user principal
     * @return User information response
     */
    fun getMe(principal: Principal): UserResponse
}