package com.cookie.note.domain.managers

import com.cookie.note.domain.models.NoteLocation

interface NoteLocationManager {

    suspend fun getLocation(): NoteLocation?
}