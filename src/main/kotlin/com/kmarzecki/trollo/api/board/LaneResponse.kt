package com.kmarzecki.trollo.api.board

import java.util.*

data class LaneResponse (
        val id: UUID,
        val name: String,
        val positionInBoard: Int
)
