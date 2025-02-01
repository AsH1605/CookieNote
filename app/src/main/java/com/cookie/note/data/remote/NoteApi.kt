package com.cookie.note.data.remote

import com.cookie.note.data.remote.dto.note.CreateNoteRequest
import com.cookie.note.data.remote.dto.note.GetNoteRequest
import com.cookie.note.data.remote.dto.note.NoteResponse
import com.cookie.note.data.remote.dto.note.UpdateNoteRequest
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NoteApi {

    @POST("createNote")
    suspend fun createNote(@Header("id_token") idToken: String, @Body createNoteRequest: CreateNoteRequest): NoteResponse

    @GET("getNotes")
    suspend fun getAllNotes(@Header("id_token") idToken: String): List<NoteResponse>

    @GET("getNote/{note_id}")
    suspend fun getNote(@Header("id_token") idToken: String, @Path("note_id") noteId: String): NoteResponse

    @DELETE("deleteNote/{note_id}")
    suspend fun deleteNote(@Header("id_token") idToken: String, @Path("note_id") noteId: String): Boolean

    @PUT("updateNote/{note_id}")
    suspend fun updateNote(@Header("id_token") idToken: String, @Path("note_id") noteId: String, @Body updateNoteRequest: UpdateNoteRequest): NoteResponse
}