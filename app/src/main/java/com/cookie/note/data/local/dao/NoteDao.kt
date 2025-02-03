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
    suspend fun insertNote(noteRecord: NoteRecord)

    @Update
    suspend fun updateNote(noteRecord: NoteRecord)

    @Delete
    suspend fun deleteNote(noteRecord: NoteRecord)

    @Query("SELECT * FROM noteRecord")
    fun getAllNotes(): Flow<List<NoteRecord>>

    @Insert
    suspend fun insertNotes(notes: List<NoteRecord>)
}