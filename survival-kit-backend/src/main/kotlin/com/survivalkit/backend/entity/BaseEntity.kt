package com.survivalkit.backend.entity

import javax.persistence.*

@MappedSuperclass
abstract class BaseEntity {
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "created", nullable = false)
    open var created: java.sql.Timestamp? = null

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "updated", nullable = false)
    open var updated: java.sql.Timestamp? = null

    @Basic(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open var status: Status? = null
}