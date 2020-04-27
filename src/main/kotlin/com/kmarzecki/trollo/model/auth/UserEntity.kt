package com.kmarzecki.trollo.model.auth

import org.hibernate.annotations.GenericGenerator
import java.util.*
import javax.persistence.*

/**
 * Entity representing the user
 */
@Entity
data class UserEntity(
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id", updatable = false, nullable = false)
        val id: UUID?,
        val username: String,
        val password: String,
        val email: String,
        val avatar_id: String,
        @ManyToMany(fetch = FetchType.EAGER)
        val roles: Set<Role>
)