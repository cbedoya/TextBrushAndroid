package com.gmt.drawing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmt.drawing.util.findPointOnLine
import com.gmt.drawing.util.slope
import com.gmt.drawing.util.toDegrees
import kotlin.math.atan

@Composable
fun TextBrushScreen() {
    val textMeasurer = rememberTextMeasurer()
    val brushes = remember {
        mutableStateListOf<TextBrushCharacters>()
    }
    var brushTextWord by remember {
        mutableStateOf(TextBrushWord("TEXT BRUSH", textMeasurer))
    }
    var changeText by remember {
        mutableStateOf(false)
    }
    var debug by remember {
        mutableStateOf(false)
    }

    Box {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        TextBrushCanvas(
            brushes = brushes,
            textMeasurer = textMeasurer,
            debug = debug,
            onDragStart = {
                brushes.add(
                    TextBrushCharacters(
                        characters = mutableListOf(),
                        start = it
                    )
                )
                brushTextWord.reset()
            },
            onDragEnd = {
                brushTextWord.reset()
            },
            onDrag = { change, dragAmount ->
                change.consume()

                val endPos = change.position
                val startPos = endPos - dragAmount
                val leftToRight = endPos.x > startPos.x
                val topToBottom = endPos.y > startPos.y

                var currentBrush = brushes.last()
                val previousCharacter = currentBrush.characters.lastOrNull()
                val previousPos = previousCharacter?.topLeft ?: currentBrush.start

                if (leftToRight && previousPos.x > endPos.x) {
                    return@TextBrushCanvas
                }

                if (!leftToRight && previousPos.x < endPos.x) {
                    return@TextBrushCanvas
                }

                if (topToBottom && previousPos.y > endPos.y) {
                    return@TextBrushCanvas
                }

                if (!topToBottom && previousPos.y < endPos.y) {
                    return@TextBrushCanvas
                }

                val slope = startPos.slope(endPos)
                val angle =
                    if (slope == Float.POSITIVE_INFINITY) 90f else atan(slope).toDegrees()

                val next = brushTextWord.nextChar()
                val mutableCharacters = currentBrush.characters.toMutableList()
                currentBrush.apply {
                    val topLeft = previousCharacter?.topLeft?.findPointOnLine(
                        slope,
                        next.size.width + spacing,
                        leftToRight,
                        topToBottom
                    )
                        ?: start

                    mutableCharacters.add(
                        TextBrushCharacter(
                            character = next,
                            rotation = angle,
                            topLeft = topLeft
                        )
                    )
                }
                currentBrush = currentBrush.copy(characters = mutableCharacters)
                brushes.removeLast()
                brushes.add(currentBrush)
            },
            onDragCancel = {
                brushTextWord.reset()
            }
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            TextBrushControls(
                onUndoClick = {
                    brushes.removeLastOrNull()
                },
                onNewBrushClick = {
                    brushes.clear()
                },
                onTextClick = {
                    changeText = true
                },
                onDebugClick = {
                    debug = !debug
                },
            )
        }

        if (changeText) {
            TextBrushWordChangeDialog(
                textBrushWord = brushTextWord,
                onDismissRequest = {
                    changeText = false
                },
                onSaveRequest = {
                    brushTextWord = TextBrushWord(it, textMeasurer)
                    changeText = false
                }
            )
        }
    }
}

@Preview
@Composable
fun TextBrushScreenPreview() {
    TextBrushScreen()
}