package com.example.dbm.inventoryappkt.presentation.view.components.shared

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun ErrorIndicator(
    errorMessage: String,
    color: Color,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    modifier: Modifier = Modifier
){
    Text(
        text = errorMessage,
        color = color,
        fontSize = fontSize,
        fontWeight = fontWeight,
        modifier = modifier,
        textAlign = TextAlign.Center)
}