package com.mini.crud.domain.user.repository

import com.mini.crud.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): List<User>
    fun findByTeamId(teamId: Long): List<User>
}