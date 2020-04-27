package com.kmarzecki.trollo.util

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
internal class DateProviderImpl : DateTimeProvider {
    override fun currentDate(): Date {
        return Date()
    }

    override fun currentLocalDateTime(): LocalDateTime? {
        return LocalDateTime.now()
    }
}