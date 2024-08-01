package com.mini.crud.domain.user.entity

import com.mini.crud.domain.team.entity.Team
import jakarta.persistence.*

@Entity
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val name: String,
    val email: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team? = null,

    @Enumerated(EnumType.STRING)
    val role: Role
)