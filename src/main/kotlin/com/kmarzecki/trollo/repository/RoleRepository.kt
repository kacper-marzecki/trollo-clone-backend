package com.kmarzecki.trollo.repository

import com.kmarzecki.trollo.model.auth.Role
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Role repository
 */
interface RoleRepository : JpaRepository<Role?, Int?> {
    fun findByName(roleName: String?): Role?
}