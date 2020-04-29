package com.kmarzecki.trollo.api.task

import com.kmarzecki.trollo.service.TaskService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.security.Principal
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/card")
@CrossOrigin(origins = ["*"], allowCredentials = "true", allowedHeaders = ["*"])
@Validated
class TaskController(
        val taskService: TaskService
) {
    @PostMapping("/{id}/task")
    fun addTask(
            @PathVariable
            cardId: UUID,
            @RequestBody @Valid
            request: CreateTaskRequest,
            principal: Principal
    ) = taskService.addTask(cardId, request, principal)


    @PostMapping("/task/{taskId}")
    fun deleteTask(
            @PathVariable taskId: UUID,
            principal: Principal
    ) = taskService.delete(taskId, principal)

    @PutMapping("/{id}/task")
    fun updateTask(@PathVariable
                   id: UUID,
                   @RequestBody @Valid
                   request: TaskUpdateRequest,
                   principal: Principal
    ) = taskService.update(id, request, principal)


}