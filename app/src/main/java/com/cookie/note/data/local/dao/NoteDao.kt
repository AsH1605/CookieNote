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

    @Query("DELETE FROM noterecord WHERE localId = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("SELECT * FROM noteRecord")
    fun getAllNotes(): Flow<List<NoteRecord>>

    @Insert
    suspend fun insertNotes(notes: List<NoteRecord>)

    @Query("SELECT * FROM noterecord WHERE localId = :noteId")
    suspend fun getNoteById(noteId: Int): NoteRecord

    @Query("UPDATE noterecord SET title = :title, content = :content, lastUpdatedAt = :lastUpdatedAt WHERE localId = :noteId")
    suspend fun updateNoteById(
        title: String,
        content: String,
        noteId: Int,
        lastUpdatedAt: Long
    )
}