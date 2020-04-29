package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.task.CreateTaskRequest
import com.kmarzecki.trollo.api.task.TaskResponse
import com.kmarzecki.trollo.api.task.TaskUpdateRequest
import java.security.Principal
import java.util.*

interface TaskService {
    fun update(taskId: UUID, request: TaskUpdateRequest, principal: Principal): TaskResponse
    fun delete(taskId: UUID, principal: Principal)
    fun addTask(cardId: UUID, request: CreateTaskRequest, principal: Principal): TaskResponse
}
