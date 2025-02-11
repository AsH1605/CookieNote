package com.cookie.note.data.remote.dto.note

import com.cookie.note.data.local.entities.NoteRecord
import kotlinx.serialization.SerialName
import java.util.Date
import kotlinx.serialization.Serializable

@Serializable
data class NoteResponse(
    val id: Int,
    val title: String,
    val content: String,
    @SerialName("user_id") val userId: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("last_updated_at") val lastUpdatedAt: String,
    val latitude: Double?,
    val longitude: Double?,
    val address: String?
)
