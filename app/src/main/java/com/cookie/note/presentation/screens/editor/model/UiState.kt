package com.cookie.note.presentation.screens.editor.model

import com.cookie.note.domain.result.DomainError

data class UiState (
    val title: String,
    val content: String,
    val error: DomainError? = null
)
//all the parameters in constructor of a data class should be a property,
//removing val makes it a parameter which is never called
