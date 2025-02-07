package com.cookie.note.di

import com.cookie.note.data.remote.NoteApi
import com.cookie.note.data.remote.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.create

@Module
@InstallIn(SingletonComponent:: class)
object ApiModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://example.com/")
            .addConverterFactory(
                Json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()))
            .build()
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit):UserApi{
        return retrofit.create()
    }

    @Provides
    @Singleton
    fun provideNotesApi(retrofit: Retrofit): NoteApi{
        return retrofit.create()
    }
}