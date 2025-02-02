package com.cookie.note

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cookie.note.domain.models.Note
import com.cookie.note.presentation.screens.list.AllNotesScreen
import com.cookie.note.presentation.screens.list.model.UiState
import com.cookie.note.presentation.theme.CookieNoteTheme
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                            ),
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
                            ),
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
                            ),
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
                            ),
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
                            ),
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
                            ),
                        )
                    ),
                    onUiEvent = {}
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CookieNoteTheme {
        Greeting("Android")
    }
}