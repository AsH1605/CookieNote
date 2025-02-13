package com.cookie.note.domain.repositories

import com.cookie.note.domain.models.Note
import com.cookie.note.domain.models.NoteLocation
import com.cookie.note.domain.result.DomainError
import com.cookie.note.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    suspend fun createNote(title: String, content: String): Result<Int, DomainError>

    suspend fun updateNote(title: String, content: String, noteId: Int): Result<Note, DomainError>

    suspend fun deleteNote(noteId: Int): Result<Unit, DomainError>

    suspend fun getNote(noteId: Int): Note?

    fun getAllNotes(): Flow<List<Note>>

    suspend fun getUsername(): String?

    suspend fun getNoteLocationById(noteId: Int): NoteLocation?
}