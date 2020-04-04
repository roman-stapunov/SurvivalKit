package com.survivalkit.backend.mapper

import com.survivalkit.backend.entity.Status
import com.survivalkit.backend.entity.UserEntity
import com.survivalkit.backend.security.model.JwtUser
import org.springframework.security.core.authority.SimpleGrantedAuthority

fun mapUserToJwtUser(userEntity: UserEntity): JwtUser {
    return JwtUser(
            userName = userEntity.username ?: "",
            pass = userEntity.password ?: "",
            authoritiesList = userEntity.rolesEntity
                    .map {
                        val simpleGrantedAuthority = SimpleGrantedAuthority(it.name)
                        simpleGrantedAuthority
                    },
            enable = Status.ACTIVE == userEntity.status
    )
}