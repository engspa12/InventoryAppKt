package com.example.dbm.inventoryappkt.presentation.view.components.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.dbm.inventoryappkt.presentation.util.getStringResourceForType
import com.example.dbm.inventoryappkt.presentation.util.toType

@Composable
inline fun <reified T> SpinnerHorizontal(
    text: String,
    options: Map<String, Int>,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    itemSelected: T,
    crossinline onItemSelected: (T) -> Unit,
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
            color = MaterialTheme.colors.onPrimary,
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
                    text = stringResource(id = itemSelected.getStringResourceForType()),
                    color = MaterialTheme.colors.onPrimary,
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
                            onItemSelected(item.key.toType(itemSelected))
                            isExpanded = false
                        }
                    ) {
                        Text(
                            text = stringResource(id = item.value)
                        )
                    }
                }
            }
        }
    }
}