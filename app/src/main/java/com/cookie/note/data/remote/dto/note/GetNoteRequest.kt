package com.cookie.note.data.remote.dto.note

data class GetNoteRequest(
    val userId: Int,
    val noteId: Int
)

