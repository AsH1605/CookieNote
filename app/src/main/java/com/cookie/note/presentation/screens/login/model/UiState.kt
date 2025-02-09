package com.cookie.note.presentation.screens.login.model

import com.cookie.note.presentation.model.Error

data class UiState(
    val username: String,
    val password: String,
    val error: Error?
)