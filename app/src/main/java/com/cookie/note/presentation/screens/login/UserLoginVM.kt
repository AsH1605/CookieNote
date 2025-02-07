package com.cookie.note.presentation.screens.login

import androidx.lifecycle.ViewModel
import com.cookie.note.domain.repositories.OnBoardingRepository
import com.cookie.note.domain.result.onFailure
import com.cookie.note.domain.result.onSuccess
import com.cookie.note.presentation.screens.login.model.Error
import com.cookie.note.presentation.screens.login.model.UiEvent
import com.cookie.note.presentation.screens.login.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserLoginVM @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(UiState("", "", null))
    val uiState = _uiState.asStateFlow()

    fun onUiEvent(uiEvent: UiEvent){
        when(uiEvent){
            UiEvent.OnLoginClicked -> TODO()
            is UiEvent.OnPasswordUpdate -> {
                _uiState.update { it.copy(password = uiEvent.updatedPassword) }
            }
            is UiEvent.OnUsernameUpdate -> {
                _uiState.update { it.copy(username = uiEvent.updatedUsername) }
            }
        }
    }

    private suspend fun login(){
        val username = uiState.value.username
        if(username == ""){
            _uiState.update { it.copy(error = Error.BLANK_USERNAME) }
            return
        }
        val password = uiState.value.password
        if(password == ""){
            _uiState.update { it.copy(error = Error.BLANK_PASSWORD) }
            return
        }
        onBoardingRepository.loginUser(username, password)
            .onSuccess {user->

            }
            .onFailure {error->

            }
    }
}