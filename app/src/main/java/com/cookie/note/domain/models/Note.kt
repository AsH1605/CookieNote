package com.cookie.note.domain.models

import java.util.Date

data class Note (
    val title: String,
    val localId: Int,
    val content: String,
    val lastUpdatedAt: Date
)