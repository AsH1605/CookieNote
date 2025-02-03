package com.cookie.note.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.note.domain.repositories.NoteRepository
import com.cookie.note.presentation.screens.list.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListNoteVM @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel(){

    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState
    init {
        viewModelScope.launch {
            initUiState()
        }
    }

    private suspend fun initUiState(){
        noteRepository.getAllNotes(1).collect{notes ->
            _uiState.update { UiState(
                username = "appleeee",
                allNotes = notes
            ) }
        }
    }
}