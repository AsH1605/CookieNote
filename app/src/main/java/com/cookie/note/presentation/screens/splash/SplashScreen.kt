package com.cookie.note.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cookie.note.R
import com.cookie.note.presentation.screens.splash.model.VMEvent
import com.cookie.note.presentation.theme.CookieNoteTheme

@Composable
fun SplashScreen(
    viewModel: SplashScreenVM,
    navigateToListNoteScreen: () -> Unit,
    navigateToLogInScreen: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.vmEvent.collect(collector = {event ->
            when(event){
                VMEvent.NavigateToListNoteScreen -> navigateToListNoteScreen()
                VMEvent.NavigateToLogInScreen -> navigateToLogInScreen()
            }
        })
    }
}

@Composable
private fun SplashScreen(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier.size(256.dp).clip(CircleShape),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Splash Screen"
        )
        Image(
            modifier = Modifier.size(256.dp),
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Splash Screen"
        )
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    CookieNoteTheme {
        SplashScreen()
    }

}