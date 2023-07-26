package com.example.simplecalculator.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CalculatingButton(
    modifier: Modifier = Modifier,
    onClick:()->Unit,
    text: String,
    textColor: Color = Color.Black.copy(.87f)
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        shape = RoundedCornerShape(0.dp),
        modifier = modifier
    ) {
        Text(text = text, fontSize = 20.sp, color = textColor, modifier = Modifier.padding(8.dp))
    }
}