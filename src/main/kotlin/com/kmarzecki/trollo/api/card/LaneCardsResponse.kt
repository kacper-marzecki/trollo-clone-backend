package com.kmarzecki.trollo.api.card

import java.util.*

data class LaneCardsResponse(
        val laneId: UUID,
        val cards: List<CardShortInfoResponse>
)
