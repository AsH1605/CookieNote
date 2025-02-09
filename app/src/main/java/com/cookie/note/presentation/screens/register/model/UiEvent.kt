package com.cookie.note.presentation.screens.register.model

sealed interface UiEvent {
    data class OnUsernameUpdate(val updatedUsername: String): UiEvent
    data class OnEmailUpdate(val updatedEmail: String): UiEvent
    data class OnPasswordUpdate(val updatedPassword: String): UiEvent
    data object OnRegisterClicked: UiEvent
    data object OnDismissError: UiEvent
}