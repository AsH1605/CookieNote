package com.cookie.note.data.manager

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey

object PreferencesKeys {
    val loggedInUserKey:Preferences.Key<Int> = intPreferencesKey("logged_in_user")
}