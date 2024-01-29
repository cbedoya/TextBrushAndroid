package com.gmt.drawing

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.toSize

data class TextBrushUiState(
    val brushes: List<TextBrushCharacters> = emptyList(),
    val brushTextWord: TextBrushWord? = null,
)

data class TextBrushCharacters(
    val characters: List<TextBrushCharacter>,
    val color: Color = Color.Black,
    val spacing: Float = 10f,
    val start: Offset,
)

data class TextBrushCharacter(
    val character: TextBrushCharSizeable,
    val rotation: Float,
    val topLeft: Offset,
)

class TextBrushWord(
    val text: String,
    val textMeasurer: TextMeasurer
) {
    private var brushCharSizeableList: List<TextBrushCharSizeable> = text.map {
        TextBrushCharSizeable(
            value = it.toString(),
            size = textMeasurer.measure(text = it.toString()).size.toSize()
        )
    }
    private var currentBrushCharIndex: Int = 0

    fun nextChar(): TextBrushCharSizeable {
        return brushCharSizeableList[currentBrushCharIndex++ % brushCharSizeableList.size]
    }

    fun reset() {
        currentBrushCharIndex = 0
    }
}

data class TextBrushCharSizeable(
    val value: String,
    val size: Size
)