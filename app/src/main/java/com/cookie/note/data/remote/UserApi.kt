package com.cookie.note.data.remote

import com.cookie.note.data.remote.dto.user.LoginUserRequest
import com.cookie.note.data.remote.dto.user.LoginUserResponse
import com.cookie.note.data.remote.dto.user.RegisterUserRequest
import com.cookie.note.data.remote.dto.user.RegisterUserResponse
import retrofit2.http.POST

interface UserApi {

    @POST("register")
    suspend fun registerUser( registerUserRequest: RegisterUserRequest): RegisterUserResponse

    @POST("login")
    suspend fun loginUser( loginUserRequest: LoginUserRequest): LoginUserResponse
}