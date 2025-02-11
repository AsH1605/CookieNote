package com.cookie.note.data.repository

import android.util.Log
import com.cookie.note.data.local.dao.NoteDao
import com.cookie.note.data.local.dao.UserDao
import com.cookie.note.data.mapper.toDomainError
import com.cookie.note.data.mapper.toNote
import com.cookie.note.data.mapper.toNoteRecord
import com.cookie.note.data.remote.NoteApi
import com.cookie.note.data.remote.dto.note.CreateNoteRequest
import com.cookie.note.domain.managers.NoteLocationManager
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
    private val locationManager: NoteLocationManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : NoteRepository {
    override suspend fun createNote(title: String, content: String): Result<Int, DomainError> = withContext(ioDispatcher) {
        val userId = preferencesManager.getLoggedInWorkerId()
            ?: return@withContext Result.Failure(DomainError.NoLoggedInWorker)
        try{
            val idToken = userDao.getIdTokenForUser(userId)
            val location = locationManager.getLocation()
            val createNoteRequest = CreateNoteRequest(
                title = title,
                content = content,
                latitude = location?.latitude,
                longitude = location?.longitude,
                address = location?.address
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
        noteId: Int
    ): Note {
        return withContext(ioDispatcher){
            val millisNow = System.currentTimeMillis()
            noteDao.updateNoteById(
                title = title,
                content = content,
                noteId = noteId,
                lastUpdatedAt = millisNow
            )
            noteDao.getNoteById(noteId).toNote()
        }
    }

    override suspend fun deleteNote(noteId: Int): Boolean {
        return withContext(ioDispatcher){
            try {
                noteDao.deleteNote(noteId)
                true
            }catch (e: IOException){
                Log.e("NoteRepositoryImpl", e.toString())
                false
            }
        }
    }

    override suspend fun getNote(noteId: Int): Note? {
        val userId = preferencesManager.getLoggedInWorkerId()?: return null
        return withContext(ioDispatcher){
            noteDao.getNoteById(noteId).toNote()
        }
    }

    override fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { noteRecords ->
            noteRecords.map { record ->
                record.toNote()
            }
        }
    }
}