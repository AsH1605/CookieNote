package com.cookie.note.presentation.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.cookie.note.R


sealed interface Error {
    data object BlankUsername: Error
    data object BlankPassword: Error
    data class BackendError(val errorMessage: String): Error
    data object BlankEmail: Error
}

@Composable
fun getErrorMessage(error: Error): String {
    return when(error){
        Error.BlankUsername-> stringResource(R.string.error_blank_username)
        Error.BlankPassword -> stringResource(R.string.error_blank_password)
        is Error.BackendError -> error.errorMessage
        Error.BlankEmail -> stringResource(R.string.error_blank_email)
    }

}