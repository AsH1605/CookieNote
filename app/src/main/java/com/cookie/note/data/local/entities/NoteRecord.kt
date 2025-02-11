package com.cookie.note.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteRecord(
    val id: Int, //this field will be generated from backend once the note is submitted so by default we are storing -1 as the id indicating that this note has not been pushed to backend yet
    val userId: Int,
    val title: String,
    val content: String,
    val createdAt: Long,
    val lastUpdatedAt: Long,
    val latitude: Double?,
    val longitude: Double?,
    val address: String?
){
    // we are not passing this variable to constructor because sqlite will autogenerate the local id
    // and if we keep this variable in constructor we will be needing two manually generate unique id for the records being saved in the database
    @PrimaryKey(autoGenerate = true) var localId: Int = 0
}