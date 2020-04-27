package com.kmarzecki.trollo.api.lane

data class LaneUpdateRequest(
        val name: String,
        val positionInBoard: Int
)
