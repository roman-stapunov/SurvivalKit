package com.survivalkit.backend.repository

import com.survivalkit.backend.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity, String> {

    fun findUsersEntityById(id: Long): UserEntity

    fun findUsersEntityByUsername(userName: String): UserEntity
}