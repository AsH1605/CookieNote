package com.cookie.note.presentation.screens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cookie.note.presentation.screens.map.model.UiState

@Composable
fun MapScreen(viewModel: MapVM) {
    val uiState by viewModel.uiState.collectAsState()
    uiState?.let { MapScreen(it) }
}

@Composable
private fun MapScreen(uiState: UiState) {
    Column {
        Text("Latitude = ${uiState.latitude}")
        Text("longitude = ${uiState.longitude}")
        Text("Area = ${uiState.address}")
    }
}