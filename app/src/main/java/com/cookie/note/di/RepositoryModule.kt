package com.cookie.note.di

import com.cookie.note.data.local.dao.NoteDao
import com.cookie.note.data.local.dao.UserDao
import com.cookie.note.data.remote.NoteApi
import com.cookie.note.data.remote.UserApi
import com.cookie.note.data.repository.NoteRepositoryImpl
import com.cookie.note.data.repository.OnBoardingRepositoryImpl
import com.cookie.note.domain.managers.PreferencesManager
import com.cookie.note.domain.repositories.NoteRepository
import com.cookie.note.domain.repositories.OnBoardingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideNoteRepository(
        dao: NoteDao,
        api: NoteApi,
        userDao: UserDao,
        preferencesManager: PreferencesManager
    ): NoteRepository {
        return NoteRepositoryImpl(
            noteDao = dao,
            userDao = userDao,
            noteApi = api,
            preferencesManager = preferencesManager
        )
    }

    @Provides
    @Singleton
    fun provideOnBoardingRepository(
        dao: UserDao,
        api: UserApi,
        preferencesManager: PreferencesManager,
    ): OnBoardingRepository {
        return OnBoardingRepositoryImpl(
            userDao = dao,
            userApi = api,
            preferencesManager = preferencesManager
        )
    }
}