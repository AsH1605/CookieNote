package com.cookie.note.data.remote.dto.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class LoginUserResponse(
    @SerialName("id_token") val idToken: String,
    val user: UserResponse
)

@Serializable
data class UserResponse(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("last_updated_at") val lastUpdatedAt: String
)
