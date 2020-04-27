package com.kmarzecki.trollo.security

import com.kmarzecki.trollo.model.auth.RegisterDto
import com.kmarzecki.trollo.model.auth.Role
import com.kmarzecki.trollo.model.auth.UserEntity
import com.kmarzecki.trollo.repository.RoleRepository
import com.kmarzecki.trollo.repository.UserRepository
import com.kmarzecki.trollo.util.CollectionUtils
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
        private val userRepository: UserRepository,
        private val roleRepository: RoleRepository
) : UserDetailsService {


    /** Check if user exists
     * @param username user username
     * @return whether a user exists
     */
    fun existsByUsername(username: String?): Boolean {
        return userRepository.existsByUsername(username)
    }

    /**
     * Save a user
     * @param dto registration data
     */
    fun saveUser(dto: RegisterDto) {
        val user = UserEntity(
                id = null,
                password = BCryptPasswordEncoder().encode(dto.password),
                username = dto.username,
                avatar_id = "DEFAULT_AVATAR",
                email = dto.email,
                roles = roleRepository.findByName("USER_ROLE")?.let { setOf(it) } ?: setOf()
        )
        userRepository.save(user)
    }

    /**
     * Load user by username
     * @param username username
     * @return User details
     * @throws UsernameNotFoundException if user with specified username is not found
     */
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        userRepository.findByUsername(username)
                ?.let {
                    val authorities = CollectionUtils.mapList<Role, GrantedAuthority>(
                            { role: Role -> SimpleGrantedAuthority(role.name) },
                            it.roles
                    )
                    return User(it.username, it.password, authorities)
                } ?: throw UsernameNotFoundException("Not found")
    }
}