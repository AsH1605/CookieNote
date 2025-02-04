package com.cookie.note.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cookie.note.presentation.screens.editor.NoteEditorScreen
import com.cookie.note.presentation.screens.editor.NoteEditorVM
import com.cookie.note.presentation.screens.list.AllNotesScreen
import com.cookie.note.presentation.screens.list.ListNoteVM

@Composable
fun App() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "note_list_screen") {
        composable(route = "note_list_screen"){
            val viewModel: ListNoteVM = hiltViewModel()
            AllNotesScreen(viewModel = viewModel, navigateToNoteEditor = {noteId->
                navController.navigate("edit_note_screen?note_id=${noteId}")
            })
        }

        composable(route = "edit_note_screen?note_id={noteId}", arguments = listOf(navArgument(
            name = "noteId",
            builder = {
                type = NavType.IntType
                nullable = false
            },
        ))){
            val viewModel: NoteEditorVM = hiltViewModel()
            NoteEditorScreen(viewModel)
        }
    }
}
