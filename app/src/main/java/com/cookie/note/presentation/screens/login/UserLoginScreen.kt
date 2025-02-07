package com.cookie.note.presentation.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cookie.note.R
import com.cookie.note.presentation.screens.login.model.UiEvent
import com.cookie.note.presentation.screens.login.model.UiState
import com.cookie.note.presentation.theme.CookieNoteTheme
import com.cookie.note.presentation.screens.login.model.Error

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserLoginScreen(
    onUiEvent: (UiEvent) -> Unit,
    uiState: UiState
) {
    if (uiState.error != null){
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(getErrorMessage(uiState.error))
            },
            confirmButton = {
                Button(onClick = {}) { }
            }
        )
    }
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = uiState.username,
                onValueChange = {updatedUsername->
                    onUiEvent(UiEvent.OnUsernameUpdate(updatedUsername))
                },
                placeholder = {
                    Text("Username")
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                value = uiState.password,
                onValueChange = {updatedPassword->
                    onUiEvent(UiEvent.OnPasswordUpdate(updatedPassword))
                },
                placeholder = {
                    Text("Password")
                },
                modifier = Modifier.fillMaxWidth(0.8f)
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(0.8f),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    "Log In"
                )
            }
        }
    }

}

@Preview
@Composable
private fun UserLoginScreenPrev() {
    CookieNoteTheme {
        UserLoginScreen(
            onUiEvent = {},
            uiState = UiState(
                username = "Username",
                password = "Password",
                error = Error.BLANK_USERNAME
            )
        )
    }

}

@Composable
private fun getErrorMessage(error: Error): String {
    return when(error){
        Error.BLANK_USERNAME -> stringResource(R.string.error_blank_username)
        Error.BLANK_PASSWORD -> stringResource(R.string.error_blank_password)
    }

}
@Preview
@Composable
private fun UserLoginScreenPrev1() {
    CookieNoteTheme(darkTheme = true) {
        UserLoginScreen(
            onUiEvent = {},
            uiState = UiState(
                username = "Username",
                password = "Password",
                error = null
            )
        )
    }

}