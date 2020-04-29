package com.kmarzecki.trollo.api.task

import java.util.*

data class CreateTaskRequest(
        val cardId: UUID,
        val text: String

)