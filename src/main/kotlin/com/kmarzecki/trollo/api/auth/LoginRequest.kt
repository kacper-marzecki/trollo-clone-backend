package com.kmarzecki.trollo.api.auth

import javax.validation.constraints.NotBlank

data class LoginRequest(
        @NotBlank
        val username: String,
        @NotBlank
        val password: String
)