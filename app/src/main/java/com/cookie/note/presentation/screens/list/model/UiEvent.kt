package com.cookie.note.presentation.screens.list.model

import android.util.Log

sealed interface UiEvent {
    data object OnCreateNoteClicked : UiEvent
}
