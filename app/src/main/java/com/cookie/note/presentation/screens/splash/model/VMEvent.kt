package com.cookie.note.presentation.screens.splash.model

sealed interface VMEvent {
    data object NavigateToLogInScreen : VMEvent
    data object NavigateToListNoteScreen : VMEvent
}