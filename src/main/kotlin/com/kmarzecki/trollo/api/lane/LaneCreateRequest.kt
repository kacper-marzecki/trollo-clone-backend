package com.kmarzecki.trollo.api.lane

import java.util.*
import javax.validation.constraints.NotBlank

data class LaneCreateRequest(
        @NotBlank
        val name: String,
        val boardId: UUID
)