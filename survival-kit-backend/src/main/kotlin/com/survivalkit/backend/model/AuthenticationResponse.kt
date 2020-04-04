package com.survivalkit.backend.model

data class AuthenticationResponse(
        val username: String,
        val token: String
)