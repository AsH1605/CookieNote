package com.cookie.note.domain.repositories

import com.cookie.note.domain.models.User
import com.cookie.note.domain.result.Result

interface OnBoardingRepository {

    suspend fun registerUser(username: String, email: String, password: String): Result<Unit>

    suspend fun loginUser(username: String, password: String): Result<User>

    suspend fun getLoggedInUserId(): Int?
}