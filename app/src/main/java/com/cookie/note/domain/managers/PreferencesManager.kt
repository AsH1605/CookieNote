package com.cookie.note.domain.managers

interface PreferencesManager {
    suspend fun getLoggedInUserId(): Int?

    suspend fun setLoggedInWorker(id: Int?)
}