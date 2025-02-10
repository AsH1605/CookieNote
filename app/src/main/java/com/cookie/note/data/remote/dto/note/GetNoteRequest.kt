package com.cookie.note.data.remote.dto.note
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetNoteRequest(
    @SerialName("user_id") val userId: Int,
    @SerialName("note_id") val noteId: Int
)

