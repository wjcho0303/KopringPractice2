package com.mini.crud.domain.team.entity

import com.mini.crud.domain.team.dto.TeamDto
import com.mini.crud.domain.user.entity.User
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
data class Team (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,

    @OneToMany(mappedBy = "team")
    val users: Set<User> = emptySet()
) {
    fun toDto(): TeamDto {
        return TeamDto(
            name = this.name
        )
    }
}