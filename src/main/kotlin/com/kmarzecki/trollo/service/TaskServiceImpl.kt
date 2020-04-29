package com.kmarzecki.trollo.service

import com.kmarzecki.trollo.api.task.CreateTaskRequest
import com.kmarzecki.trollo.api.task.TaskResponse
import com.kmarzecki.trollo.api.task.TaskUpdateRequest
import com.kmarzecki.trollo.exception.ElementNotFoundException
import com.kmarzecki.trollo.model.CardTaskEntity
import com.kmarzecki.trollo.repository.CardRepository
import com.kmarzecki.trollo.repository.TaskRepository
import com.kmarzecki.trollo.util.str
import org.springframework.stereotype.Service
import java.security.Principal
import java.util.*
import javax.transaction.Transactional

@Service
@Transactional
class TaskServiceImpl(
        val taskRepository: TaskRepository,
        val cardRepository: CardRepository
) : TaskService {
    override fun update(taskId: UUID, request: TaskUpdateRequest, principal: Principal): TaskResponse {
        val task = taskRepository.findByIdAndCard_Lane_Board_Users_Username(taskId, principal.name)
                ?: throw ElementNotFoundException("Task", taskId.str)
        task.isComplete = request.isComplete
        task.text = request.text
        return taskRepository.save(task)
                .let(this::map)
    }

    private fun map(it: CardTaskEntity): TaskResponse = TaskResponse(
            id = it.id!!,
            isComplete = it.isComplete,
            text = it.text
    )

    override fun delete(taskId: UUID, principal: Principal) {
        taskRepository.deleteByIdAndCard_Lane_Board_Users_Username(taskId, principal.name)
    }

    override fun addTask(cardId: UUID, request: CreateTaskRequest, principal: Principal): TaskResponse {
        val card = cardRepository.findByIdAndLane_Board_Users_Username(request.cardId, principal.name)
                ?: throw ElementNotFoundException("Card", request.cardId.str)
        return taskRepository.save(CardTaskEntity(
                text = request.text,
                isComplete = false,
                card = card
        )).let {
            card.tasks.add(it)
            cardRepository.save(card)
            it
        }.let(this::map)
    }
}