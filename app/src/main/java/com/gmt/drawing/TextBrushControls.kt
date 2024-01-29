package com.gmt.drawing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TextBrushControls(
    onUndoClick: () -> Unit = {},
    onNewBrushClick: () -> Unit = {},
    onTextClick: () -> Unit = {},
    onDebugClick: () -> Unit = {},
) {
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        IconButton(onClick = onUndoClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_undo),
                contentDescription = null,
                tint = Color.White
            )
        }
        IconButton(onClick = onNewBrushClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_new_brush),
                contentDescription = null,
                tint = Color.White
            )
        }
        IconButton(onClick = onTextClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_text),
                contentDescription = null,
                tint = Color.White
            )
        }
        IconButton(onClick = onDebugClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_debug),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun TextBrushControlsPreview() {
    TextBrushControls()
}