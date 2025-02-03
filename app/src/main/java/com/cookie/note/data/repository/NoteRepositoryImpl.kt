package com.cookie.note.data.repository

import com.cookie.note.data.local.dao.NoteDao
import com.cookie.note.data.local.entities.NoteRecord
import com.cookie.note.data.mapper.toNote
import com.cookie.note.domain.models.Note
import com.cookie.note.domain.repositories.NoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class NoteRepositoryImpl(private val noteDao: NoteDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : NoteRepository {
    override suspend fun createNote(title: String, content: String, userId: Int) = withContext(ioDispatcher) {
        //we are creating timestamp and storing it in a variable instead of directly generating at use to ensure that createdate and last updated_at for the first time are same
        val millisNow = System.currentTimeMillis()
        val noteRecord = NoteRecord(
            id = -1,
            userId = userId,
            title = title,
            content = content,
            createdAt = millisNow,
            lastUpdatedAt = millisNow
        )
        noteDao.insertNote(noteRecord)
    }

    override suspend fun updateNote(
        title: String,
        content: String,
        userId: Int,
        noteId: Int
    ): Note {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(userId: Int, noteId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getNote(userId: Int, noteId: Int): Note {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(userId: Int): Flow<List<Note>> {
        return noteDao.getAllNotes().map { noteRecords ->
            noteRecords.map { record ->
                record.toNote()
            }
        }
    }

}