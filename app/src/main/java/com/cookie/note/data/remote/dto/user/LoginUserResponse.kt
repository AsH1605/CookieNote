package com.cookie.note.data.remote.dto.user

import java.util.Date

data class LoginUserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val idToken: String,
    val createdAt: Date,
    val lastUpdatedAt: Date
)
