package com.cookie.note.data.remote.dto.user

import java.util.Date

data class LoginUserResponse(
    val idToken: String,
    val user: UserResponse
)

data class UserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val createdAt: String,
    val lastUpdatedAt: String
)
