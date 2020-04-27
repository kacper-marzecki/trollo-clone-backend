package com.kmarzecki.trollo

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class TrolloApplication {
    @Bean
    fun mapper(): ObjectMapper {
        return ObjectMapper().registerModule(KotlinModule())
    }
}

fun main(args: Array<String>) {
    runApplication<TrolloApplication>(*args)
}