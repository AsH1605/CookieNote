package com.cookie.note.domain.repositories

import com.cookie.note.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun createNote(title: String, content: String, userId: String): Note

    suspend fun updateNote(title: String, content: String, userId: String, noteId: Int): Note

    suspend fun deleteNote(userId: String, noteId: Int): Boolean

    suspend fun getNote(userId: String, noteId: Int): Note

    fun getAllNotes(userId: String): Flow<List<Note>>
}