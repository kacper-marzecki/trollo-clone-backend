package com.kmarzecki.trollo.api

import com.kmarzecki.trollo.exception.OperationNotPermittedException
import com.kmarzecki.trollo.util.MessageUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

/**
 * Global exception handler
 */
@ControllerAdvice
class GlobalExceptionHandler(
        val messagingTemplate: SimpMessageSendingOperations
) {

    /**
     * Handler for exceptions related to authentication
     * @param t Thrown exception
     * @return Error response
     */
    @ExceptionHandler(BadCredentialsException::class, AuthenticationException::class)
    fun handleAuthenticationException(t: Throwable?): ResponseEntity<*> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build<Any>()
    }

    /**
     * Handler for exceptions related to forbidden operations attempted by a user
     * @param t Thrown exception
     * @return Error Response
     */
    @ExceptionHandler(OperationNotPermittedException::class)
    fun handleOperationNotPermittedException(t: OperationNotPermittedException): ResponseEntity<*> {
        when{
            !t.user.isNullOrBlank() && !t.customMessage.isNullOrBlank() -> {
                MessageUtils.sendError(messagingTemplate, t.user, t.customMessage)
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(t.customMessage)
    }

}