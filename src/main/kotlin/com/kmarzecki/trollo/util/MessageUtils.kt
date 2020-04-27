package com.kmarzecki.trollo.util

import org.springframework.messaging.simp.SimpMessageSendingOperations

object MessageUtils {
    const val NOTIFICATION_TOPIC = "/topic/notification"

    fun sendError(template: SimpMessageSendingOperations, user: String, error: String) {
        template.convertAndSendToUser(user, NOTIFICATION_TOPIC, error)
    }
}