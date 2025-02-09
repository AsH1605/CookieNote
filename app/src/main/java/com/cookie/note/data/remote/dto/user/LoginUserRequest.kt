package com.cookie.note.data.remote.dto.user

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest (
    val username: String,
    val password: String
)