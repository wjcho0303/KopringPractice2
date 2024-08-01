package com.mini.crud.domain.team.controller

import com.mini.crud.domain.team.dto.TeamDto
import com.mini.crud.domain.team.service.TeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/teams")
class TeamController @Autowired constructor(private val teamService: TeamService) {

    @GetMapping
    fun getAllTeams(): List<TeamDto> = teamService.getAllTeams()

    @GetMapping("/{id}")
    fun getTeamById(
        @PathVariable id: Long): ResponseEntity<TeamDto> {
        val team = teamService.getTeamById(id).get()
        return ResponseEntity.ok(team)
    }

    @PostMapping
    fun createTeam(
        @RequestParam teamName: String) {
        teamService.createTeam(TeamDto(teamName))
    }

    @PatchMapping("/{id}")
    fun updateTeam(
        @PathVariable id: Long,
        @RequestParam teamName: String) {
        teamService.updateTeam(id, TeamDto(teamName))
    }

    @DeleteMapping("/{id}")
    fun deleteTeam(
        @PathVariable id: Long
    ) {
        teamService.deleteTeam(id)
    }
}