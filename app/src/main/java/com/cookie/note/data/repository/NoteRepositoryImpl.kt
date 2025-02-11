package com.cookie.note.data.repository

import android.util.Log
import com.cookie.note.data.local.dao.NoteDao
import com.cookie.note.data.local.dao.UserDao
import com.cookie.note.data.mapper.toDomainError
import com.cookie.note.data.mapper.toNote
import com.cookie.note.data.mapper.toNoteRecord
import com.cookie.note.data.remote.NoteApi
import com.cookie.note.data.remote.dto.note.CreateNoteRequest
import com.cookie.note.domain.managers.PreferencesManager
import com.cookie.note.domain.models.Note
import com.cookie.note.domain.repositories.NoteRepository
import com.cookie.note.domain.result.DomainError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import com.cookie.note.domain.result.Result

class NoteRepositoryImpl(
    private val noteDao: NoteDao,
    private val userDao: UserDao,
    private val noteApi: NoteApi,
    private val preferencesManager: PreferencesManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteRepository {
    override suspend fun createNote(title: String, content: String): Result<Int, DomainError> = withContext(ioDispatcher) {
        val userId = preferencesManager.getLoggedInWorkerId()
            ?: return@withContext Result.Failure(DomainError.NoLoggedInWorker)
        try{
            val idToken = userDao.getIdTokenForUser(userId)
            val createNoteRequest = CreateNoteRequest(
                title = title,
                content = content
            )
            val response = noteApi.createNote(
                idToken = idToken,
                createNoteRequest = createNoteRequest
            )
            val localId = noteDao.insertNote(response.toNoteRecord()).toInt()
            Result.Success(localId)

        }catch (e: HttpException){
            Result.Failure(e.toDomainError())
        }
    }

    override suspend fun updateNote(
        title: String,
        content: String,
        userId: Int,
        noteId: Int
    ): Note {
        return withContext(ioDispatcher){
            val millisNow = System.currentTimeMillis()
            noteDao.updateNoteById(
                title = title,
                content = content,
                userId = userId,
                noteId = noteId,
                lastUpdatedAt = millisNow
            )
            noteDao.getNoteById(userId, noteId).toNote()
        }
    }

    override suspend fun deleteNote(userId: Int, noteId: Int): Boolean {
        return withContext(ioDispatcher){
            try {
                noteDao.deleteNote(userId, noteId)
                true
            }catch (e: IOException){
                Log.e("NoteRepositoryImpl", e.toString())
                false
            }
        }
    }

    override suspend fun getNote(userId: Int, noteId: Int): Note {
        return withContext(ioDispatcher){
            noteDao.getNoteById(userId, noteId).toNote()
        }
    }

    override fun getAllNotes(userId: Int): Flow<List<Note>> {
        return noteDao.getAllNotes().map { noteRecords ->
            noteRecords.map { record ->
                record.toNote()
            }
        }
    }
}