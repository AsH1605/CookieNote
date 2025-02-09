package com.cookie.note.presentation.screens.login.model

sealed interface VMEvent {
    data object NavigateToRegisterUser: VMEvent
    data object NavigateToListNoteScreen: VMEvent
}