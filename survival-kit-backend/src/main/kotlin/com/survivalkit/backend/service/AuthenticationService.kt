package com.survivalkit.backend.service

import com.survivalkit.backend.model.AuthenticationRequest
import com.survivalkit.backend.model.AuthenticationResponse

interface AuthenticationService {
    fun authenticateAndCreateToken(authenticationRequest: AuthenticationRequest): AuthenticationResponse
}