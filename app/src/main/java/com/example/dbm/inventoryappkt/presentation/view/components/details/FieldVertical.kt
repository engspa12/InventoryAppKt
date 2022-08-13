package com.example.dbm.inventoryappkt.presentation.view.components.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
fun FieldVertical(
    title: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    value: String,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .wrapContentHeight(),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            color = MaterialTheme.colors.onPrimary,
            fontSize = fontSize,
            fontWeight = fontWeight,
            text = title
        )
        Text(
            modifier = Modifier
                .wrapContentHeight(),
            color = MaterialTheme.colors.onPrimary,
            fontSize = fontSize,
            fontWeight = fontWeight,
            text = value
        )
    }
}