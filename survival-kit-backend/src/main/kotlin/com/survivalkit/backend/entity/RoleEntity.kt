package com.survivalkit.backend.entity

import javax.persistence.*

@Entity
@Table(name = "roles", schema = "public", catalog = "postgres")
open class RoleEntity : BaseEntity() {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    open var id: Long? = null

    @Basic
    @Column(name = "name", nullable = false)
    open var name: String? = null

    @ManyToMany(mappedBy = "rolesEntity", targetEntity = UserEntity::class)
    open var usersEntity: Set<UserEntity> = mutableSetOf()
}

