package com.mini.crud.domain.user.service

import com.mini.crud.domain.team.repository.TeamRepository
import com.mini.crud.domain.user.dto.UserDto
import com.mini.crud.domain.user.entity.User
import com.mini.crud.domain.user.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.NoSuchElementException
import java.util.Optional

@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository,
    private val teamRepository: TeamRepository
) {

    fun getAllUsers(): List<UserDto> {
        return userRepository.findAll().map { it.toDto() }
    }

    fun getUserById(id: Long) : Optional<UserDto> {
        return userRepository.findById(id).map { it.toDto() }
    }

    fun createUser(userDto: UserDto) {
        val team = userDto.teamName?. let { teamRepository.findByName(it).firstOrNull() }
        val user = User(
            name = userDto.name,
            email = userDto.email,
            team = team,
            role = userDto.role
        )
    }

    fun updateUser(id: Long, userDto: UserDto) {
        if (userRepository.existsById(id)) {
            val team = userDto.teamName?. let { teamRepository.findByName(it).firstOrNull() }
            val userToUpdate = User(
                id = id,
                name = userDto.name,
                email = userDto.email,
                team = team,
                role = userDto.role
            )
            userRepository.save(userToUpdate)
        } else {
            throw NoSuchElementException("User with id $id not found")
        }
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }
}