package com.kmarzecki.trollo.security

import com.kmarzecki.trollo.exception.OperationNotPermittedException
import com.kmarzecki.trollo.model.auth.LoginDto
import com.kmarzecki.trollo.model.auth.LoginResponse
import com.kmarzecki.trollo.model.auth.RegisterDto
import com.kmarzecki.trollo.model.auth.UserResponse
import com.kmarzecki.trollo.repository.UserRepository
import com.kmarzecki.trollo.util.InternationalizationUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service
import java.security.Principal
import javax.transaction.Transactional

@Service
class AuthenticationServiceImpl(
        val userRepository: UserRepository,
        val jwtTokenService: JwtTokenService,
        val authenticationManager: AuthenticationManager,
        val userService: UserDetailsServiceImpl
) : AuthenticationService {
    override fun login(dto: LoginDto): LoginResponse {
        val username = dto.username
        authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(username, dto.password)
        )
        val token = jwtTokenService.createToken(username, userRepository.findByUsername(username)!!.roles)
        return LoginResponse(username, token)
    }

    @Transactional
    override fun register(registerDto: RegisterDto) {
        if (userService.existsByUsername(registerDto.username)) {
            throw OperationNotPermittedException(customMessage = InternationalizationUtil.userExists(registerDto.username))
        }
        userService.saveUser(registerDto)
    }

    override fun getMe(principal: Principal): UserResponse {
        return UserResponse(principal.name)
    }
}