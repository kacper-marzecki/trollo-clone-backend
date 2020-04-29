package com.kmarzecki.trollo.api.card

import com.kmarzecki.trollo.model.CardTaskEntity
import com.kmarzecki.trollo.model.FileEntity
import com.kmarzecki.trollo.model.LaneEntity
import java.util.*

data class CardResponse(
        val id: UUID,
        val name: String,
        val description: String,
        val positionInLane: Int,
        val tasks: List<UUID>,
        val files: List<FileEntity>
)
