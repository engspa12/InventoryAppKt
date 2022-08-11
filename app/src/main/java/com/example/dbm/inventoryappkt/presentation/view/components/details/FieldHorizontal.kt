package com.example.dbm.inventoryappkt.presentation.view.components.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun FieldHorizontal(
    title: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    value: String,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .padding(start =  10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = Modifier
                .padding(end = 10.dp)
        )
        Text(
            text = value,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
        )
    }
}