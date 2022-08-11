package com.example.dbm.inventoryappkt.presentation.view.components.add

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.global.Constants

@Composable
fun SpinnerHorizontal(
    text: String,
    options: List<String>,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    itemSelected: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = fontWeight,
            modifier = Modifier
                .padding(end = 10.dp)
        )
        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        isExpanded = true
                    }
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = itemSelected,
                    fontSize = fontSize,
                    fontWeight = fontWeight
                )
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "")
            }
            DropdownMenu(
                modifier = Modifier.fillMaxWidth(0.55f),
                expanded = isExpanded,
                onDismissRequest = {
                    isExpanded = false
                }) {
                for (item in options) {
                    DropdownMenuItem(
                        onClick = {
                            onItemSelected(item)
                            isExpanded = false
                        }
                    ) {
                        Text(text = item)
                    }
                }
            }
        }
    }
}