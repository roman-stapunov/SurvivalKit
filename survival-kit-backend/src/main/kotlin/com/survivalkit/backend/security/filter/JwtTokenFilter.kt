package com.survivalkit.backend.security.filter

import com.survivalkit.backend.security.service.JwtTokenProvider
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtTokenFilter(
        private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {

    private val prefix: String = "SK_TOKEN_"

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, filter: FilterChain?) {
        resolveToken(request as HttpServletRequest)?.let {
            if (jwtTokenProvider.validationToken(it)) {
                val authentication = jwtTokenProvider.getAuthentication(it)
                SecurityContextHolder.getContext().authentication = authentication
            }
        }
        filter?.doFilter(request, response)
    }

    private fun resolveToken(httpServletRequest: HttpServletRequest): String? {
        val header = httpServletRequest.getHeader("Authorization")

        if (header != null && header.startsWith(prefix)) {
            return header.substring(prefix.length)
        }
        return null
    }
}