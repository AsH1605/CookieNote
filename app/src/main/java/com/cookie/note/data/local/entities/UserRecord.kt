package com.cookie.note.data.local.entities

import androidx.room.Entity
import java.util.Date

@Entity
data class UserRecord(
    val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val createdAt: Date,
    val lastUpdatedAt: Date,
    val idToken: String
)