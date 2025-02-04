package com.cookie.note.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.cookie.note.data.local.entities.NoteRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insertNote(noteRecord: NoteRecord): Long

    @Update
    suspend fun updateNote(noteRecord: NoteRecord)

    @Delete
    suspend fun deleteNote(noteRecord: NoteRecord)

    @Query("SELECT * FROM noteRecord")
    fun getAllNotes(): Flow<List<NoteRecord>>

    @Insert
    suspend fun insertNotes(notes: List<NoteRecord>)

    @Query("SELECT * FROM noterecord WHERE userId = :userId AND localId = :noteId")
    suspend fun getNoteById(userId: Int, noteId: Int): NoteRecord

    @Query("UPDATE noterecord SET title = :title, content = :content, lastUpdatedAt = :lastUpdatedAt WHERE userId = :userId AND localId = :noteId")
    suspend fun updateNoteById(
        title: String,
        content: String,
        userId: Int,
        noteId: Int,
        lastUpdatedAt: Long
    )
}