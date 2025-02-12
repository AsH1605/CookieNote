package com.cookie.note.presentation.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.note.domain.repositories.NoteRepository
import com.cookie.note.domain.repositories.OnBoardingRepository
import com.cookie.note.presentation.screens.list.model.UiEvent
import com.cookie.note.presentation.screens.list.model.UiState
import com.cookie.note.presentation.screens.list.model.VMEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListNoteVM @Inject constructor(
    private val noteRepository: NoteRepository,
    private val onBoardingRepository: OnBoardingRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState

    private val _vmEvent = MutableSharedFlow<VMEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            initUiState()
        }
    }

    private suspend fun initUiState() {
        val username = noteRepository.getUsername()
        noteRepository.getAllNotes().collect { notes ->
            _uiState.update {
                UiState(
                    username = username,
                    allNotes = notes
                )
            }
        }
    }

    fun onUiEvent(event: UiEvent) {
        when (event) {
            UiEvent.OnCreateNoteClicked -> {}
            is UiEvent.OnDeleteNote -> viewModelScope.launch {
                noteRepository.deleteNote(noteId = event.noteId)
            }

            is UiEvent.OnNoteClicked -> {}
            UiEvent.OnProfileClicked ->{
                _uiState.update {
                    it?.copy(
                        isContextMenuVisible = !it.isContextMenuVisible
                    )
                }
            }
            UiEvent.OnLogoutClicked -> viewModelScope.launch{
                onBoardingRepository.logoutUser()
                _vmEvent.emit(VMEvent.NavigateToLogInScreen)
            }
        }
    }
}