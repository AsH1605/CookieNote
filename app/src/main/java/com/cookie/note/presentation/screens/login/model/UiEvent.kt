package com.cookie.note.presentation.screens.login.model

sealed interface UiEvent {
    data object OnLoginClicked: UiEvent
    data class OnUsernameUpdate(val updatedUsername: String): UiEvent
    data class OnPasswordUpdate(val updatedPassword: String): UiEvent
    data object OnDismissError: UiEvent
}