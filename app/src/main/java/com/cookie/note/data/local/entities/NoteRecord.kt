package com.cookie.note.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteRecord(
    val id: Int,
    val userId: Int,
    val title: String,
    val content: String,
    val createdAt: Long,
    val lastUpdatedAt: Long
){
    @PrimaryKey(autoGenerate = true) var localId: Int = 0
}