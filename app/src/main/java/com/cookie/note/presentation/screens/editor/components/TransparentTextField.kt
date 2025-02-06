package com.cookie.note.presentation.screens.editor.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String)->Unit,
    style: TextStyle = TextStyle.Default,
    singleLine: Boolean = false,
    hint: String = "",
    enabled: Boolean = true,
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = style.copy(color = MaterialTheme.colorScheme.onBackground),
            singleLine = singleLine,
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            enabled = enabled,
        )
        if(value == "" && hint != ""){
            Text(
                text = hint,
                style = style,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
            )
        }
    }
}