package com.mini.crud.domain.user.controller

import com.mini.crud.domain.user.dto.UserDto
import com.mini.crud.domain.user.entity.Role
import com.mini.crud.domain.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
    private val userService: UserService
) {

    @GetMapping
    fun getAllUsers(): List<UserDto> {
        return userService.getAllUsers()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDto> {
        val user = userService.getUserById(id).get()
        return ResponseEntity.ok(user)
    }

    @PostMapping
    fun createUser(
        @RequestParam name: String,
        @RequestParam email: String,
        @RequestParam teamName: String
    ) {
        val userDto = UserDto(
            name = name,
            email = email,
            teamName = teamName
        )
        userService.createUser(userDto)
    }

    @PatchMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userDto: UserDto
    ) {
        userService.updateUser(id, userDto)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

    @GetMapping("/search")
    fun getUserByName(
        @RequestParam name: String
    ) : ResponseEntity<List<UserDto>> {
        val users = userService.findUsersByName(name)
        return ResponseEntity.ok(users)
    }

    @GetMapping("/teams/{teamId}")
    fun getUsersByTeamId(
        @PathVariable teamId: Long
    ) : ResponseEntity<List<UserDto>> {
        val users = userService.findUsersByTeamId(teamId)
        return ResponseEntity.ok(users)
    }
}