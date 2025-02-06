package com.cookie.note.data.repository

import com.cookie.note.domain.result.Result
import android.util.Log
import com.cookie.note.data.local.dao.UserDao
import com.cookie.note.data.local.entities.UserRecord
import com.cookie.note.data.mapper.toUser
import com.cookie.note.data.remote.UserApi
import com.cookie.note.data.remote.dto.user.LoginUserRequest
import com.cookie.note.data.remote.dto.user.RegisterUserRequest
import com.cookie.note.domain.managers.PreferencesManager
import com.cookie.note.domain.models.User
import com.cookie.note.domain.repositories.OnBoardingRepository
import com.cookie.note.domain.util.isoTimestampToDate
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class OnBoardingRepositoryImpl(
    private val userApi: UserApi,
    private val userDao: UserDao,
    private val preferencesManager: PreferencesManager,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : OnBoardingRepository {

    companion object{
        private const val TAG = "OnBoardingRepositoryImpl"
    }

    override suspend fun registerUser(username: String, email: String, password: String): Result<Unit> {
        return withContext(ioDispatcher){
            try {
                val request = RegisterUserRequest(username, email, password)
                userApi.registerUser(request)
                Result.Success(Unit)
            }catch (e: HttpException){
                Log.e(TAG, e.message())
                Result.Failure(e.message())
            }
        }
    }

    override suspend fun loginUser(username: String, password: String): Result<User> {
        return withContext(ioDispatcher){
            try {
                val loginRequest = LoginUserRequest(username, password)
                val response = userApi.loginUser(loginRequest)
                val userRecord = UserRecord(
                    id = response.user.id,
                    username = response.user.username,
                    email = response.user.email,
                    password = response.user.password,
                    createdAt = isoTimestampToDate(response.user.createdAt).time,
                    lastUpdatedAt = isoTimestampToDate(response.user.lastUpdatedAt).time,
                    idToken = response.idToken
                )
                userDao.addUser(userRecord)
                preferencesManager.setLoggedInWorker(userRecord.id)
                Result.Success(userRecord.toUser())
            }catch (e: HttpException){
                Log.e(TAG, e.message())
                Result.Failure(e.message())
            }
        }
    }
}