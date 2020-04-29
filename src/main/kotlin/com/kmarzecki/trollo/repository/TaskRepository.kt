package com.kmarzecki.trollo.repository

import com.kmarzecki.trollo.model.CardTaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TaskRepository: JpaRepository<CardTaskEntity, UUID> {
    fun findByIdAndCard_Lane_Board_Users_Username(id: UUID, username: String): CardTaskEntity?
    fun deleteByIdAndCard_Lane_Board_Users_Username(id: UUID, username: String)
}