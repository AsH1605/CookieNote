package com.cookie.note.data.mapper

import com.cookie.note.data.local.entities.UserRecord
import com.cookie.note.domain.models.User

fun UserRecord.toUser(): User{
    return User(username = username)
}