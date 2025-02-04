package com.cookie.note.presentation.screens.editor.model

data class UiState (
    val title: String,
    val content: String
)
//all the parameters in constructor of a data class should be a property,
//removing val makes it a parameter which is never called
