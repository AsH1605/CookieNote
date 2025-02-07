package com.cookie.note.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cookie.note.domain.repositories.OnBoardingRepository
import com.cookie.note.presentation.screens.splash.model.VMEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenVM @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository
): ViewModel() {

    private val _vmEvent = MutableSharedFlow<VMEvent>()
    val vmEvent = _vmEvent.asSharedFlow()

    init {
        viewModelScope.launch { checkLoginStatus() }
    }

    private suspend fun checkLoginStatus(){
        val userId = onBoardingRepository.getLoggedInUserId()
        delay(5000L)
        if(userId == null){
            _vmEvent.emit(VMEvent.NavigateToLogInScreen)
        }
        else{
            _vmEvent.emit(VMEvent.NavigateToListNoteScreen)
        }
    }
}