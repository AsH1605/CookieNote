package com.cookie.note.data.mapper

import android.util.Log
import com.cookie.note.domain.result.DomainError
import kotlinx.serialization.json.Json
import retrofit2.HttpException

fun HttpException.toDomainError(): DomainError{
    val errorBody = this.response()?.errorBody()?.string()
    return if(errorBody != null){
        try {
            Json.decodeFromString<DomainError.ApiError>(errorBody)
        }catch (e: Exception){
            Log.e("main", "csdcs",e)
            DomainError.UnknownError(errorBody)
        }
    }
    else{
        DomainError.UnknownError("Some unknown Http Exception with code ${code()}")
    }
}