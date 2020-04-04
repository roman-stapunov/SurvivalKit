package com.survivalkit.backend.security.service

import com.survivalkit.backend.mapper.mapUserToJwtUser
import com.survivalkit.backend.repository.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("jwtTokenService")
class JwtTokenService(private var userRepository: UserRepository) : UserDetailsService {

    var log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun loadUserByUsername(username: String?): UserDetails {
        val userEntity = username?.let {
            userRepository.findUsersEntityByUsername(it)
        } ?: throw UsernameNotFoundException("User name with username: $username not found")
        log.debug("Load user with username: $username")
        return mapUserToJwtUser(userEntity)
    }
}