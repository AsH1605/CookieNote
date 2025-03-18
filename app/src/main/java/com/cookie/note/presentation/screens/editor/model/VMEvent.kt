package com.cookie.note.presentation.screens.editor.model

sealed interface VMEvent {
    data object NavigateUp: VMEvent
}