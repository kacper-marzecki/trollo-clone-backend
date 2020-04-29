package com.kmarzecki.trollo.repository

import com.kmarzecki.trollo.model.CardEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CardRepository : JpaRepository<CardEntity, UUID> {
    fun findAllByLane_IdIn(laneIds: List<UUID>): List<CardEntity>
    fun findAllByLane_Board_Id(boardId: UUID): List<CardEntity>
    fun findByIdAndLane_Board_Users_Username(id: UUID, username: String): CardEntity?
}
