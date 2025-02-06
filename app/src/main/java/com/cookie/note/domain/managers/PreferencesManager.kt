package com.cookie.note.domain.managers

import kotlinx.coroutines.flow.Flow

interface PreferencesManager {
    suspend fun getLoggedInWorkerId(): Int?

    suspend fun setLoggedInWorker(id: Int)
}