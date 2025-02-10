package com.cookie.note.domain.result

import kotlinx.serialization.Serializable

@Serializable
sealed interface DomainError {
    @Serializable
    data class ApiError(val code: Int, val error: String): DomainError
    @Serializable
    data class UnknownError(val error: String): DomainError

    fun getErrorMessage(): String{
        return when(this){
            is ApiError -> this.error
            is UnknownError -> this.error
        }
    }
}
