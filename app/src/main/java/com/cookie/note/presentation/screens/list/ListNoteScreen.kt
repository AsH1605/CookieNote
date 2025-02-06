package com.cookie.note.presentation.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cookie.note.domain.models.Note
import com.cookie.note.presentation.screens.list.model.UiEvent
import com.cookie.note.presentation.screens.list.model.UiState
import com.cookie.note.presentation.theme.CookieNoteTheme
import java.util.Date

@Composable
fun AllNotesScreen(viewModel: ListNoteVM, navigateToNoteEditor: (Int) -> Unit) {
    val uiState by viewModel.uiState.collectAsState()
    uiState?.let { state ->
        AllNotesScreen(uiState = state, onUiEvent = { event ->
            when (event) {
                UiEvent.OnCreateNoteClicked -> navigateToNoteEditor(-1)
                is UiEvent.OnNoteClicked -> navigateToNoteEditor(event.noteId)
                else -> {
                    viewModel.onUiEvent(event)
                }
            }
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AllNotesScreen(
    uiState: UiState,
    onUiEvent: (UiEvent) -> Unit)
{
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        "Welcome, " + uiState.username,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = "Profile"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onUiEvent(UiEvent.OnCreateNoteClicked) }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add note",
                    modifier = Modifier.size(23.dp)
                )
            }
        }
    ) { padding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(uiState.allNotes) { note ->
                val swipeDismissedState =
                    rememberSwipeToDismissBoxState(confirmValueChange = { value ->
                        onUiEvent(UiEvent.OnDeleteNote(noteId = note.localId))
                        value == SwipeToDismissBoxValue.StartToEnd
                    })
                SwipeToDismissBox(
                    swipeDismissedState,
                    backgroundContent = {}
                ) {
                    NoteCard(note.title, note.content, onClick = {
                        onUiEvent(UiEvent.OnNoteClicked(note.localId))
                    })
                }
            }
        }
    }
}

@Composable
fun NoteCard(noteTitle: String, noteContent: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.tertiary,
            contentColor = MaterialTheme.colorScheme.onTertiary,
            disabledContainerColor = MaterialTheme.colorScheme.tertiary,
            disabledContentColor = MaterialTheme.colorScheme.secondary
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = noteTitle,
                modifier = Modifier,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                maxLines = 1
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = noteContent,
                modifier = Modifier,
                textAlign = TextAlign.Left,
                fontSize = 12.sp,
                lineHeight = 16.sp,
                overflow = TextOverflow.Ellipsis,
                maxLines = 3
            )
        }
    }
}

@Preview
@Composable
private fun AllNotesScreenPreview() {
    CookieNoteTheme {
        AllNotesScreen(
            uiState = UiState(
                username = "apple",
                allNotes = listOf(
                    Note(
                        title = "hello",
                        content = "bye bye",
                        lastUpdatedAt = Date(),
                        localId = 1
                    ),
                    Note(
                        title = "hello",
                        content = "bye bye bye bye bye bye bye bye bey bye bye bye bye bye bye bye bye beybye bye bye bye bye bye bye bye bey ",
                        lastUpdatedAt = Date(),
                        localId = 1
                    ),
                    Note(
                        title = "hello",
                        content = "bye bye",
                        lastUpdatedAt = Date(),
                        localId = 1
                    )
                )
            ),
            onUiEvent = {}
        )
    }
}

@Preview
@Composable
private fun AllNotesScreenPreview1() {
    CookieNoteTheme(darkTheme = true) {
        AllNotesScreen(
            uiState = UiState(
                username = "apple",
                allNotes = listOf(
                    Note(
                        title = "hello",
                        content = "bye bye",
                        lastUpdatedAt = Date(),
                        localId = 1
                    ),
                    Note(
                        title = "hello",
                        content = "bye bye bye bye bye bye bye bye bey bye bye bye bye bye bye bye bye beybye bye bye bye bye bye bye bye bey ",
                        lastUpdatedAt = Date(),
                        localId = 1
                    ),
                    Note(
                        title = "hello",
                        content = "bye bye",
                        lastUpdatedAt = Date(),
                        localId = 1
                    )
                )
            ),
            onUiEvent = {}
        )
    }
}