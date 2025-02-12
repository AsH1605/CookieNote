package com.cookie.note.presentation.screens.list.model

import android.util.Log

sealed interface UiEvent {
    data object OnCreateNoteClicked : UiEvent
    data class OnNoteClicked(val noteId: Int): UiEvent
    data class OnDeleteNote(val noteId: Int): UiEvent
    data object OnProfileClicked: UiEvent
    data object OnLogoutClicked: UiEvent
}
