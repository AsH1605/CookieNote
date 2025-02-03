package com.cookie.note.di

import android.content.Context
import com.cookie.note.data.local.CookieDatabase
import com.cookie.note.data.local.dao.NoteDao
import com.cookie.note.data.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    @Singleton
    @Provides
    fun provideCookieDatabase(@ApplicationContext context: Context): CookieDatabase {
        return CookieDatabase.getCookieDatabase(context)
    }

    @Singleton
    @Provides
    fun provideUserDao(database: CookieDatabase): UserDao {
        return database.userDao()
    }

    @Singleton
    @Provides
    fun provideNoteDao(database: CookieDatabase): NoteDao{
        return database.noteDao()
    }
}