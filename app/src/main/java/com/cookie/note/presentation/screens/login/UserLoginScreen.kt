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
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookie.note.R
import com.cookie.note.presentation.screens.login.model.UiEvent
import com.cookie.note.presentation.screens.login.model.UiState
import com.cookie.note.presentation.theme.CookieNoteTheme
import com.cookie.note.presentation.model.Error
import com.cookie.note.presentation.model.getErrorMessage
import com.cookie.note.presentation.screens.login.model.VMEvent

@Composable
fun UserLoginScreen(
    viewModel: UserLoginVM,
    navigateToRegisterUser: () -> Unit,
    navigateToListNoteScreen: () ->Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.vmEvent.collect(collector = {event->
            when(event){
                VMEvent.NavigateToRegisterUser -> navigateToRegisterUser()
                VMEvent.NavigateToListNoteScreen -> navigateToListNoteScreen()
            }
        })
    }
    UserLoginScreen(uiState = uiState, onUiEvent = {event->
        viewModel.onUiEvent(event)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun UserLoginScreen(
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
                Button(onClick = {onUiEvent(UiEvent.OnDismissError)}) {
                    Text(
                        text = stringResource(R.string.dismiss)
                    )
                }
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
                onClick = {
                    onUiEvent(UiEvent.OnLoginClicked)
                },
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
                error = Error.BlankUsername
            )
        )
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