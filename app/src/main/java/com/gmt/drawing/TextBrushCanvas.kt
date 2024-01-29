package com.gmt.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText

@Composable
fun TextBrushCanvas(
    brushes: List<TextBrushCharacters>,
    textMeasurer: TextMeasurer,
    debug: Boolean,
    onDragStart: (Offset) -> Unit = { },
    onDragEnd: () -> Unit = { },
    onDragCancel: () -> Unit = { },
    onDrag: (change: PointerInputChange, dragAmount: Offset) -> Unit = { _, _ -> },
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures(
                    onDragStart = onDragStart,
                    onDragEnd = onDragEnd,
                    onDrag = onDrag,
                    onDragCancel = onDragCancel
                )
            }
    ) {
        brushes.forEach { brush ->
            brush.characters.forEach { brushCharacter ->
                rotate(brushCharacter.rotation, brushCharacter.topLeft) {
                    if (debug) {
                        drawDebugger(textBrush = brushCharacter, brush = brush)
                    }
                    drawText(
                        textMeasurer = textMeasurer,
                        text = brushCharacter.character.value,
                        topLeft = brushCharacter.topLeft,
                        size = brushCharacter.character.size,
                    )
                }
            }
        }
    }
}