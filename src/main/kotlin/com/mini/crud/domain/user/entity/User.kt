package com.mini.crud.domain.user.entity

import com.mini.crud.domain.team.entity.Team
import com.mini.crud.domain.user.dto.UserDto
import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,
    val name: String,
    val email: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val team: Team? = null,

    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER
) {
    fun toDto(): UserDto {
        return UserDto(
            name = this.name,
            email = this.email,
            teamName = this.team?.name,
        )
    }
}