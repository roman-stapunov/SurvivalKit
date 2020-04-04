package com.survivalkit.backend.entity

import javax.persistence.*

@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
open class UserEntity : BaseEntity() {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    open var id: Long? = null

    @Basic
    @Column(name = "username", nullable = false)
    open var username: String? = null

    @Basic
    @Column(name = "email", nullable = false)
    open var email: String? = null

    @Basic
    @Column(name = "first_name", nullable = false)
    open var firstName: String? = null

    @Basic
    @Column(name = "last_name", nullable = false)
    open var lastName: String? = null

    @Basic
    @Column(name = "password", nullable = false)
    open var password: String? = null

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = RoleEntity::class)
    @JoinTable(name = "user_roles", schema = "public", catalog = "postgres",
            joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
            inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")])
    open var rolesEntity: Set<RoleEntity> = mutableSetOf()
}

