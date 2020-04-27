package com.kmarzecki.trollo.util

import java.time.LocalDateTime
import java.util.*

/**
 * Provider for time data
 */
interface DateTimeProvider {
    /**
     * @return current date
     */
    fun currentDate(): Date

    /**
     * @return current datetime
     */
    fun currentLocalDateTime(): LocalDateTime?
}