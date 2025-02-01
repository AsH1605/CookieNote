package com.cookie.note.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class NoteRecord(
    val id: Int,
    val userId: Int,
    val title: String,
    val content: String,
    val createdAt: Date,
    val lastUpdatedAt: Date
){
    @PrimaryKey(autoGenerate = true) var localId: Int = 0
}