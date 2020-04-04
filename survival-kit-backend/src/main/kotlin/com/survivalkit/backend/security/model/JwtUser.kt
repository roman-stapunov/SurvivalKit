package com.survivalkit.backend.security.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class JwtUser(
        val userName: String = "",
        val pass: String = "",
        val authoritiesList: List<GrantedAuthority>,
        val enable: Boolean
) : UserDetails {

    override fun getUsername(): String = userName
    override fun getPassword(): String = pass
    override fun getAuthorities(): List<GrantedAuthority> = authoritiesList
    override fun isEnabled(): Boolean = enable
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
}