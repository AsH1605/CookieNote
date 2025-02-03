package com.cookie.note.domain.repositories

import com.cookie.note.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun createNote(title: String, content: String, userId: Int)

    suspend fun updateNote(title: String, content: String, userId: Int, noteId: Int): Note

    suspend fun deleteNote(userId: Int, noteId: Int): Boolean

    suspend fun getNote(userId: Int, noteId: Int): Note

    fun getAllNotes(userId: Int): Flow<List<Note>>
}