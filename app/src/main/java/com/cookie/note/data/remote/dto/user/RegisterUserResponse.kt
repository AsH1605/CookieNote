package com.cookie.note.data.remote.dto.user

import kotlinx.serialization.SerialName
import java.util.Date
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserResponse(
    val message: String
)

