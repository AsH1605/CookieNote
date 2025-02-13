package com.cookie.note

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cookie.note.data.local.CookieDatabase
import com.cookie.note.data.local.entities.NoteRecord
import com.cookie.note.domain.models.Note
import com.cookie.note.presentation.App
import com.cookie.note.presentation.screens.list.AllNotesScreen
import com.cookie.note.presentation.screens.list.ListNoteVM
import com.cookie.note.presentation.screens.list.model.UiState
import com.cookie.note.presentation.screens.login.UserLoginScreen
import com.cookie.note.presentation.screens.map.MapScreen
import com.cookie.note.presentation.theme.CookieNoteTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CookieNoteTheme {
                App()
            }
        }
    }
}
