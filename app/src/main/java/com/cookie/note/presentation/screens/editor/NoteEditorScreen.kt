package com.cookie.note.presentation.screens.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cookie.note.presentation.screens.editor.model.UiEvent
import com.cookie.note.presentation.screens.editor.model.UiState

@Composable
fun NoteEditorScreen(viewModel: NoteEditorVM) {
    val uiState by viewModel.uiState.collectAsState()
    NoteEditorScreen(uiState = uiState, onUiEvent = {event->
        viewModel.onUiEvent(event)
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteEditorScreen(
    uiState: UiState,
    onUiEvent: (UiEvent) -> Unit
){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    Scaffold (
        modifier = Modifier,
        topBar = {
            LargeTopAppBar(
                title = {
                    TextField(
                        value = uiState.title,
                        onValueChange = {updatedTitle->
                            onUiEvent(UiEvent.OnTitleUpdate(updatedTitle))
                        }
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onUiEvent(UiEvent.OnNavigateUpClicked)},
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back button"
                            )
                        }
                    )
                },
                actions = {
                    IconButton(
                        onClick = {onUiEvent(UiEvent.OnSavedNoteClicked)}
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Note save"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ){padding->
        Column (
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ){
            TextField(
                value = uiState.content,
                onValueChange = {updatedContent->
                    onUiEvent(UiEvent.OnContentUpdate(updatedContent))
                }
            )
        }
    }
}

@Preview
@Composable
private fun NoteEditorScreenPrev() {
    NoteEditorScreen(
        uiState = UiState(
            title = "hiii",
            content = "how are you"
        ),
        onUiEvent = {}
    )
}