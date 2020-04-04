package com.survivalkit.backend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

@Configuration
@ConfigurationProperties("application.security.jwt")
class JwtConfig {

    var securityWord: String = ""
        get() = Base64.getEncoder().encodeToString(field.toByteArray())

    var tokenExpired: Long = 3600

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}