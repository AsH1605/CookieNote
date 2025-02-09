package com.cookie.note.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError (
    val code: NetworkErrorCode,
    val message: String
)
