package com.cookie.note.presentation.screens.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.note.domain.managers.NoteLocationManager
import com.cookie.note.domain.repositories.NoteRepository
import com.cookie.note.presentation.screens.map.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapVM @Inject constructor(
    private val noteRepository: NoteRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableStateFlow<UiState?>(null)
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

    private suspend fun initUiState(){
        noteId?.let { id->
            val location = noteRepository.getNoteLocationById(id)
            if (location != null){
                _uiState.update {
                    UiState(longitude = location.longitude, latitude = location.latitude, address = location.address)
                }
            }

        }

    }
}