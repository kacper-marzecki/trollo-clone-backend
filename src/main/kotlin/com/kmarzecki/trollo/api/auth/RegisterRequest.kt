package com.kmarzecki.trollo.api.auth

data class RegisterRequest(
        val username: String,
        val password: String,
        val email: String
)