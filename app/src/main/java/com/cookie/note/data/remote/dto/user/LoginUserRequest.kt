package com.cookie.note.data.remote.dto.user

data class LoginUserRequest (
    val username: String,
    val email: String,
    val password: String
)