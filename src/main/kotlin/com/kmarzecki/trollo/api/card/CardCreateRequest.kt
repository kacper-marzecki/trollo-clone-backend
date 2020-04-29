package com.kmarzecki.trollo.api.card

import java.util.*
import javax.validation.constraints.NotBlank

data class CardCreateRequest(
        val laneId: UUID,
        @NotBlank
        val name: String,
        @NotBlank
        val description: String
)