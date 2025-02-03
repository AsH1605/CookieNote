package com.cookie.note.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cookie.note.presentation.screens.list.AllNotesScreen
import com.cookie.note.presentation.screens.list.ListNoteVM

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "note_list_screen") {
        composable(route = "note_list_screen"){
            val viewModel: ListNoteVM = hiltViewModel()
            AllNotesScreen(viewModel = viewModel, navigateToNoteEditor = {
                navController.navigate("edit_note_screen")
            })
        }

        composable(route = "edit_note_screen"){
            Text("apple")
        }
    }
}
