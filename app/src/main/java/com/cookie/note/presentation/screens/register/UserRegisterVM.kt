package com.cookie.note.presentation.screens.register

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.note.domain.repositories.OnBoardingRepository
import com.cookie.note.domain.result.onFailure
import com.cookie.note.domain.result.onSuccess
import com.cookie.note.presentation.model.Error
import com.cookie.note.presentation.screens.register.model.VMEvent
import com.cookie.note.presentation.screens.register.model.UiEvent
import com.cookie.note.presentation.screens.register.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserRegisterVM @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
) : ViewModel(){
    private val _uiState = MutableStateFlow(UiState("", "", "", null))
    val uiState = _uiState.asStateFlow()

    private val _vmEvent = MutableSharedFlow<VMEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    fun onUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            is UiEvent.OnEmailUpdate -> {
                _uiState.update { it.copy(email = uiEvent.updatedEmail) }
            }
            is UiEvent.OnPasswordUpdate -> {
                _uiState.update { it.copy(password = uiEvent.updatedPassword) }
            }
            UiEvent.OnRegisterClicked -> {
                viewModelScope.launch { register() }
            }
            is UiEvent.OnUsernameUpdate -> {
                _uiState.update { it.copy(username = uiEvent.updatedUsername) }
            }

            UiEvent.OnDismissError -> {
                _uiState.update { it.copy(error = null) }
            }
        }
    }

    private suspend fun register(){
        val username = uiState.value.username
        if(username == ""){
            _uiState.update { it.copy(error = Error.BlankUsername) }
            return
        }
        val password = uiState.value.password
        if(password == ""){
            _uiState.update { it.copy(error = Error.BlankPassword) }
            return
        }
        val email = uiState.value.email
        if(email == ""){
            _uiState.update { it.copy(error = Error.BlankEmail) }
            return
        }
        onBoardingRepository.registerUser(username = username, password =  password, email = email)
            .onSuccess {user->
                _vmEvent.emit(VMEvent.NavigateToLogin)
            }
            .onFailure {error->
                _uiState.update { it.copy(error = Error.BackendError(error)) }
            }
    }
}