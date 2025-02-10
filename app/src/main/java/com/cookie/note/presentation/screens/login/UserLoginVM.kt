package com.cookie.note.presentation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.note.domain.repositories.OnBoardingRepository
import com.cookie.note.domain.result.DomainError
import com.cookie.note.domain.result.UserErrorCodes
import com.cookie.note.domain.result.onFailure
import com.cookie.note.domain.result.onSuccess
import com.cookie.note.presentation.model.Error
import com.cookie.note.presentation.screens.login.model.UiEvent
import com.cookie.note.presentation.screens.login.model.UiState
import com.cookie.note.presentation.screens.login.model.VMEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserLoginVM @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState("", "", null))
    val uiState = _uiState.asStateFlow()

    private val _vmEvent = MutableSharedFlow<VMEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    fun onUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            UiEvent.OnLoginClicked -> {
                viewModelScope.launch { login() }
            }
            is UiEvent.OnPasswordUpdate -> {
                _uiState.update { it.copy(password = uiEvent.updatedPassword) }
            }
            is UiEvent.OnUsernameUpdate -> {
                _uiState.update { it.copy(username = uiEvent.updatedUsername) }
            }

            UiEvent.OnDismissError -> {
                _uiState.update { it.copy(error = null) }
            }
        }
    }

    private suspend fun login(){
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
        onBoardingRepository.loginUser(username, password)
            .onSuccess {user->
                _vmEvent.emit(VMEvent.NavigateToListNoteScreen)
            }
            .onFailure {error->
                if(error is DomainError.ApiError && error.code == UserErrorCodes.USER_DOES_NOT_EXIST.code){
                    _vmEvent.emit(VMEvent.NavigateToRegisterUser)
                }
                else{
                    _uiState.update { it.copy(error = Error.BackendError(error.getErrorMessage())) }
                }
            }
    }
}