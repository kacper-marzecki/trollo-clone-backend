package com.kmarzecki.trollo.repository

import com.kmarzecki.trollo.model.auth.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * User repository
 */
@Repository
interface UserRepository : JpaRepository<UserEntity?, Int?> {
    /**
     * Check if a user with username exists
     * @param username user username
     * @return whether a user exists
     */
    fun existsByUsername(username: String?): Boolean

    /**
     * Find user bu username
     * @param username user username
     * @return User
     */
    fun findByUsername(username: String): UserEntity?

    /**
     * Find all users with usernames
     * @param usernames usernames
     * @return Set of users
     */
    fun findAllByUsernameIn(usernames: Set<String?>?): Set<UserEntity?>?
}