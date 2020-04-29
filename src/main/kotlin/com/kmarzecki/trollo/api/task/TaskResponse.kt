package com.kmarzecki.trollo.api.task

import java.util.*

data class TaskResponse (
    val id: UUID,
    val text: String,
    val isComplete: Boolean
)
