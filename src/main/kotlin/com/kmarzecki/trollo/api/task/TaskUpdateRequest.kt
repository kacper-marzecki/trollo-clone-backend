package com.kmarzecki.trollo.api.task

import java.util.*
import javax.validation.constraints.NotBlank

data class TaskUpdateRequest(
        @NotBlank
        val text: String,
        val isComplete: Boolean
)
