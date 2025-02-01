package com.cookie.note.domain.repositories

import com.cookie.note.domain.models.User

interface OnBoardingRepository {

    suspend fun registerUser(username: String, email: String, password: String): Boolean

    suspend fun loginUser(username: String, password: String): User
}