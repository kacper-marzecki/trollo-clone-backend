package com.kmarzecki.trollo.repository

import com.kmarzecki.trollo.model.LaneEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface LaneRepository : JpaRepository<LaneEntity, UUID>{
    fun findAllByBoard_Id(boardId: UUID): List<LaneEntity>
    fun existsByNameAndBoard_Id(name: String, boardId: UUID): Boolean
}