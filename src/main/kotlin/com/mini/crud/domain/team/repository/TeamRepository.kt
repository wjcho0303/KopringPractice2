package com.mini.crud.domain.team.repository

import com.mini.crud.domain.team.entity.Team
import org.springframework.data.jpa.repository.JpaRepository

interface TeamRepository : JpaRepository<Team, Long> {
    fun findByName(name: String): List<Team>
}