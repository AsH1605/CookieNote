package com.cookie.note.data.remote.dto.note

import kotlinx.serialization.Serializable

@Serializable
data class CreateNoteRequest(
    val title: String,
    val content: String,
    val latitude: Double?,
    val longitude: Double?,
    val address: String?
)

