package com.cookie.note.di

import android.content.Context
import com.cookie.note.data.manager.LocationManagerImpl
import com.cookie.note.data.manager.PreferencesManagerImpl
import com.cookie.note.domain.managers.NoteLocationManager
import com.cookie.note.domain.managers.PreferencesManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ManagerModule {
    
    @Provides
    @Singleton
    fun providePreferencesManager(@ApplicationContext context: Context): PreferencesManager{
        return PreferencesManagerImpl(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideLocationManager(): NoteLocationManager {
        return LocationManagerImpl()
    }
}