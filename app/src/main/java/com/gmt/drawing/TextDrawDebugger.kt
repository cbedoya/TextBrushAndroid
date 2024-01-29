package com.gmt.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

fun DrawScope.drawDebugger(textBrush: TextBrushCharacter, brush: TextBrushCharacters) {
    drawRect(
        color = Color.Green,
        topLeft = textBrush.topLeft,
        size = textBrush.character.size,
        style = Stroke(width = 1f)
    )
    drawLine(
        color = Color.Red,
        start = textBrush.topLeft,
        end = Offset(textBrush.topLeft.x + textBrush.character.size.width + brush.spacing, textBrush.topLeft.y),
        strokeWidth = 1f
    )
}