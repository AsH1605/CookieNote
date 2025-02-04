package com.cookie.note.presentation.screens.editor.model

sealed interface UiEvent {
    data object OnNavigateUpClicked: UiEvent
    data object OnSavedNoteClicked: UiEvent
    data class OnTitleUpdate(val updatedTitle: String): UiEvent
    data class OnContentUpdate(val updatedContent: String): UiEvent
}