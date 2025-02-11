package com.cookie.note.presentation.screens.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.note.domain.repositories.NoteRepository
import com.cookie.note.domain.result.DomainError
import com.cookie.note.domain.result.onFailure
import com.cookie.note.domain.result.onSuccess
import com.cookie.note.presentation.screens.editor.model.UiEvent
import com.cookie.note.presentation.screens.editor.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteEditorVM @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState("", ""))
    val uiState = _uiState.asStateFlow()

    private var noteId: Int? = null

    init {
        val id = savedStateHandle.get<Int>("noteId")
        if (id != -1) {
            noteId = id
        }
        viewModelScope.launch {
            initUiState()
        }
    }

    private suspend fun initUiState() {
        noteId?.let { id ->
            val note = noteRepository.getNote(id)
            if (note != null) {
                _uiState.update { it.copy(title = note.title, content = note.content) }
            }
        }
    }

    fun onUiEvent(event: UiEvent): Unit {
        when (event) {
            is UiEvent.OnContentUpdate -> onContentUpdate(event.updatedContent)
            UiEvent.OnNavigateUpClicked -> {}
            UiEvent.OnSavedNoteClicked -> viewModelScope.launch {
                saveNote()
            }

            is UiEvent.OnTitleUpdate -> onTitleUpdate(event.updatedTitle)
        }
    }

    private suspend fun saveNote() {
        if (noteId != null) {
            updateNote()
        } else {
            createNote()
        }
    }

    private suspend fun createNote() {
        val title = uiState.value.title
        val content = uiState.value.content
        noteRepository.createNote(
            title = title,
            content = content
        ).onSuccess { id ->
            noteId = id
        }.onFailure { e ->
            _uiState.update { it.copy(error = e) }
        }
    }

    private suspend fun updateNote() {
        noteId?.let { id ->
            val title = uiState.value.title
            val content = uiState.value.content
            noteRepository.updateNote(
                title = title,
                content = content,
                noteId = id
            )
        }
    }

    private fun onContentUpdate(updatedContent: String) {
        _uiState.update { it.copy(content = updatedContent) }
    }

    private fun onTitleUpdate(updatedTitle: String) {
        _uiState.update { it.copy(title = updatedTitle) }
    }
}
