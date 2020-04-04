package com.survivalkit.backend.security.service

import com.survivalkit.backend.config.JwtConfig
import com.survivalkit.backend.entity.RoleEntity
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class JwtTokenProvider(
        private val jwtConfig: JwtConfig,
        private val jwtTokenService: UserDetailsService
) {

    fun createToken(username: String, roles: Set<RoleEntity>): String {

        val claims = Jwts.claims().setSubject(username)
        claims["roles"] = roles.map {
            it.name
        }

        val localDateTime = LocalDateTime.now().plusSeconds(jwtConfig.tokenExpired)

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date())
                .setExpiration(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, jwtConfig.securityWord)
                .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails = jwtTokenService.loadUserByUsername(usernameFromJwt(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun validationToken(token: String): Boolean = Jwts.parser()
            .setSigningKey(jwtConfig.securityWord)
            .parseClaimsJws(token)
            .body
            .expiration
            .after(Date())

    private fun usernameFromJwt(token: String): String {
        return Jwts
                .parser()
                .setSigningKey(jwtConfig.securityWord)
                .parseClaimsJws(token)
                .body
                .subject
    }
}