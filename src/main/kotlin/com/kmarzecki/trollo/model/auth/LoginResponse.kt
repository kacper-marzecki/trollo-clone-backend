package com.kmarzecki.trollo.model.auth

data class LoginResponse (
    val username: String,
    val token: String
)