package com.cookie.note.data.remote

import com.cookie.note.data.remote.dto.user.LoginUserRequest
import com.cookie.note.data.remote.dto.user.LoginUserResponse
import com.cookie.note.data.remote.dto.user.RegisterUserRequest
import com.cookie.note.data.remote.dto.user.RegisterUserResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("users/register")
    suspend fun registerUser(@Body registerUserRequest: RegisterUserRequest): RegisterUserResponse

    @POST("users/login")
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest): LoginUserResponse
}