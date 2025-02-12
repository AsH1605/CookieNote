package com.cookie.note.data.manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.cookie.note.domain.managers.PreferencesManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

//private val name makes it a property and no means parameter of constructor which is not accessed by functions
class PreferencesManagerImpl(context: Context): PreferencesManager {

    private val preferences = context.settingsDataStore

    override suspend fun getLoggedInUserId(): Int? {
        return preferences.data.map { prefs->
            prefs[PreferencesKeys.loggedInUserKey]
        }
            .first()
    }

    override suspend fun setLoggedInWorker(id: Int?) {
        preferences.edit { prefs->
            if(id == null){
                prefs.remove(PreferencesKeys.loggedInUserKey)
            }
            else{
                prefs[PreferencesKeys.loggedInUserKey] = id
            }

        }
    }
}