package com.survivalkit.backend.service.impl

import com.survivalkit.backend.model.AuthenticationRequest
import com.survivalkit.backend.model.AuthenticationResponse
import com.survivalkit.backend.repository.UserRepository
import com.survivalkit.backend.security.service.JwtTokenProvider
import com.survivalkit.backend.service.AuthenticationService
import com.survivalkit.backend.service.DecryptService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
        private val authenticationManager: AuthenticationManager,
        private val jwtTokenProvider: JwtTokenProvider,
        private val userRepository: UserRepository,
        private val decryptService: DecryptService
) : AuthenticationService {
    override fun authenticateAndCreateToken(authenticationRequest: AuthenticationRequest): AuthenticationResponse {
        val passwordDecrypt = decryptService.decode(authenticationRequest.password)
        authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                authenticationRequest.username,
                passwordDecrypt
        ))
        val userEntity = userRepository.findUsersEntityByUsername(authenticationRequest.username)
        val username = userEntity.username!!
        val token = jwtTokenProvider.createToken(username, userEntity.rolesEntity)

        return AuthenticationResponse(username, token)
    }
}