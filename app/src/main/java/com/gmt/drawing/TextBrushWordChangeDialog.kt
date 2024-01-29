package com.gmt.drawing

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TextBrushWordChangeDialog(
    modifier: Modifier = Modifier,
    textBrushWord: TextBrushWord,
    onDismissRequest: () -> Unit = {  },
    onSaveRequest: (String) -> Unit = {  }
) {
    var text by remember {
        mutableStateOf(textBrushWord.text)
    }
    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(10.dp,5.dp,10.dp,10.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Brush Text:",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                OutlinedTextField(value = text, onValueChange = { text = it})
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(Color.LightGray),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(
                            "CANCEL",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    TextButton(onClick = { onSaveRequest(text) }) {
                        Text(
                            "SAVE",
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TextBrushWordChangeDialogPreview() {
    TextBrushWordChangeDialog(
        textBrushWord = TextBrushWord("HOLA MUNDO", rememberTextMeasurer())
    )
}