package com.survivalkit.backend.model

data class AuthenticationRequest(
        val username: String,
        val password: String
)