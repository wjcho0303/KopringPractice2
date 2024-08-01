package com.mini.crud.domain.user.dto

import com.mini.crud.domain.user.entity.Role

data class UserResponse(
    val name: String,
    val email: String,
    val teamName: String?,
    val role: Role
)
