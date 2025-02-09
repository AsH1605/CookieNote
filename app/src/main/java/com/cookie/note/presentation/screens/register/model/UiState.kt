package com.cookie.note.presentation.screens.register.model

import com.cookie.note.presentation.model.Error

data class UiState (
    val username: String,
    val email: String,
    val password: String,
    val error: Error?
)