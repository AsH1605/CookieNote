package com.cookie.note.presentation.screens.register.model

sealed interface VMEvent {
    data object NavigateToLogin: VMEvent
}
