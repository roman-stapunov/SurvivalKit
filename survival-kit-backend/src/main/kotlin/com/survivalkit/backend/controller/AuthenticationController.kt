package com.survivalkit.backend.controller

import com.survivalkit.backend.model.AuthenticationRequest
import com.survivalkit.backend.model.AuthenticationResponse
import com.survivalkit.backend.service.AuthenticationService
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:3000"])
@RequestMapping("/api/v1/auth/")
class AuthenticationController(
        private val authenticationService: AuthenticationService
) {

    @PostMapping(path = ["login"])
    fun login(@RequestBody authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        return authenticationService.authenticateAndCreateToken(authenticationRequest)
    }
}
