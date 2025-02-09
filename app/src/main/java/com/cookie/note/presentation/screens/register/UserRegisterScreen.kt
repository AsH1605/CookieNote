package com.cookie.note.presentation.screens.register

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
import com.cookie.note.presentation.model.getErrorMessage
import com.cookie.note.presentation.screens.register.model.UiEvent
import com.cookie.note.presentation.screens.register.model.UiState
import com.cookie.note.presentation.screens.register.model.VMEvent
import com.cookie.note.presentation.theme.CookieNoteTheme

@Composable
fun UserRegisterScreen(
    viewModel: UserRegisterVM,
    navigateToLoginScreen: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.vmEvent.collect(collector = {event->
            when(event){
                VMEvent.NavigateToLogin -> navigateToLoginScreen()
            }
        })
    }
    UserRegisterScreen(uiState = uiState, onUiEvent = {event->
        viewModel.onUiEvent(event)
    })
}

@Composable
private fun UserRegisterScreen(
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
                value = uiState.email,
                onValueChange = {updatedEmail->
                    onUiEvent(UiEvent.OnEmailUpdate(updatedEmail))
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
                    "Register"
                )
            }
        }
    }

}

@Preview
@Composable
private fun UserLoginScreenPrev() {
    CookieNoteTheme {
        UserRegisterScreen(
            onUiEvent = {},
            uiState = UiState(
                username = "Username",
                email = "Email",
                password = "Password",
                error = null,
            )
        )
    }

}

@Preview
@Composable
private fun UserLoginScreenPrev1() {
    CookieNoteTheme(darkTheme = true) {
        UserRegisterScreen(
            onUiEvent = {},
            uiState = UiState(
                username = "Username",
                email = "Email",
                password = "Password",
                error = null,
            )
        )
    }

}