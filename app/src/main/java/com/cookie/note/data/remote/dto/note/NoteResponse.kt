package com.cookie.note.data.remote.dto.note

import com.cookie.note.data.local.entities.NoteRecord
import java.util.Date

data class NoteResponse(
    val id: Int,
    val title: String,
    val content: String,
    val userId: Int,
    val createdAt: Date,
    val lastUpdatedAt: Date
)
