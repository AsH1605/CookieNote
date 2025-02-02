package com.cookie.note.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserRecord(
    @PrimaryKey val id: Int,
    val username: String,
    val email: String,
    val password: String,
    val createdAt: Long,
    val lastUpdatedAt: Long,
    val idToken: String
)