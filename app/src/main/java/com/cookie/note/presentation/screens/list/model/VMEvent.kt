package com.cookie.note.presentation.screens.list.model

sealed interface VMEvent {
    data object NavigateToLogInScreen: VMEvent
}