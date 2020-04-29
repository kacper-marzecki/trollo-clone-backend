package com.kmarzecki.trollo.repository

import com.kmarzecki.trollo.model.BoardEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface BoardRepository : JpaRepository<BoardEntity?, UUID?> {
    fun existsByNameAndUsers_Username(name: String, users_username: String): Boolean
    fun findAllByUsers_Username(users_username: String): List<BoardEntity>
    fun deleteAlLByIdAndUsers_Username(id: UUID, users_username: String)
    fun existsByIdAndUsers_Username(id: UUID, users_username: String): Boolean
}