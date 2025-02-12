package com.cookie.note.presentation.screens.list.model

import com.cookie.note.domain.models.Note

data class UiState (
    val username: String?,
    val allNotes: List<Note>,
    var isContextMenuVisible: Boolean = false
)