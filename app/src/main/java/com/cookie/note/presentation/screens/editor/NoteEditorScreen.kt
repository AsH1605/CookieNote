package com.cookie.note.presentation.screens.editor

import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookie.note.presentation.screens.editor.components.TransparentTextField
import com.cookie.note.presentation.screens.editor.model.UiEvent
import com.cookie.note.presentation.screens.editor.model.UiState
import com.cookie.note.presentation.theme.CookieNoteTheme

@Composable
fun NoteEditorScreen(viewModel: NoteEditorVM, navigateUp: ()-> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    NoteEditorScreen(uiState = uiState, onUiEvent = {event->
        if(event is UiEvent.OnNavigateUpClicked){
            navigateUp()
        }
        else{
            viewModel.onUiEvent(event)
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NoteEditorScreen(
    uiState: UiState,
    onUiEvent: (UiEvent) -> Unit
){
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val isCollapsed by remember { derivedStateOf { scrollBehavior.state.collapsedFraction > 0.5 } }

    Scaffold (
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    TransparentTextField(
                        value = uiState.title,
                        onValueChange = {updatedTitle->
                            onUiEvent(UiEvent.OnTitleUpdate(updatedTitle))
                        },
                        singleLine = true,
                        hint = "Title",
                        style = MaterialTheme.typography.headlineMedium,
                        enabled = !isCollapsed
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
                .padding(horizontal = 16.dp)
        ){
            TransparentTextField(
                value = uiState.content,
                onValueChange = {updatedContent->
                    onUiEvent(UiEvent.OnContentUpdate(updatedContent))
                },
                modifier = Modifier.fillMaxSize(),
                singleLine = false,
                hint = "Type Here...",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
private fun NoteEditorScreenPrev() {
    CookieNoteTheme {
        NoteEditorScreen(
            uiState = UiState(
                title = "hiii",
                content = "how are you"
            ),
            onUiEvent = {}
        )
    }
}

@Preview
@Composable
private fun NoteEditorScreenPrev1() {
    CookieNoteTheme (darkTheme = true){
        NoteEditorScreen(
            uiState = UiState(
                title = "hiii",
                content = "how are you"
            ),
            onUiEvent = {}
        )
    }

}