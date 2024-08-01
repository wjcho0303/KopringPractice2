package com.mini.crud.domain.team.service

import com.mini.crud.domain.team.dto.TeamResponse
import com.mini.crud.domain.team.entity.Team
import com.mini.crud.domain.team.repository.TeamRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import java.util.Optional

@Service
class TeamService @Autowired constructor(
    private val teamRepository: TeamRepository
) {

    fun getAllTeams(): List<TeamResponse> {
        return teamRepository.findAll().map { it.toDto() }
    }

    fun getTeamById(id: Long): Optional<TeamResponse> {
        return teamRepository.findById(id).map { it.toDto() }
    }

    fun createTeam(teamResponse: TeamResponse): Team {
        val team = Team(name = teamResponse.name)
        return teamRepository.save(team)
    }

    fun updateTeam(id: Long, teamDto: TeamResponse) {
        if (teamRepository.existsById(id)) {
            val teamToUpdate = Team(
                id = id,
                name = teamDto.name
            )
            teamRepository.save(teamToUpdate)
        } else {
            throw NoSuchElementException("Team with id $id not found")
        }
    }

    fun deleteTeam(id: Long) {
        teamRepository.deleteById(id)
    }
}