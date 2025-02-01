package com.cookie.note.data.remote.dto.user

import com.cookie.note.data.local.entities.UserRecord
import java.util.Date

data class RegisterUserRequest (
    val username: String,
    val email: String,
    val password: String
)
