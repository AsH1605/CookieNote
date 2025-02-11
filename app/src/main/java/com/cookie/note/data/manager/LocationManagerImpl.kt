package com.cookie.note.data.manager

import com.cookie.note.domain.managers.NoteLocationManager
import com.cookie.note.domain.models.NoteLocation

//TODO: Use actual location service from Google Playstore
class LocationManagerImpl: NoteLocationManager {
    override suspend fun getLocation(): NoteLocation? {
        return NoteLocation(21.128686, 81.765711, "IIIT Naya Raipur")
    }
}