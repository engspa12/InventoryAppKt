package com.example.dbm.inventoryappkt.presentation.view.components.details

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.inventoryappkt.R

@Composable
fun QuantityEditor(
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
    ) {
        Button(
            onClick = {
                onDecreaseQuantity()
                      },
            modifier = Modifier
                .padding(start = 10.dp, end = 5.dp)
                .border(width = 2.dp, color = MaterialTheme.colors.onPrimary, shape = RoundedCornerShape(18.dp))
                .height(50.dp)
                .weight(1f),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.regular_button_background)
            )
        ) {
            Text(
                text = stringResource(id = R.string.quantity_minus),
                letterSpacing = 4.sp
            )
        }
        Button(
            onClick = {
                onIncreaseQuantity()
                      },
            modifier = Modifier
                .padding(start = 5.dp, end = 5.dp)
                .border(width = 2.dp, color = MaterialTheme.colors.onPrimary, shape = RoundedCornerShape(18.dp))
                .height(50.dp)
                .weight(1f),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.regular_button_background)
            )
        ) {
            Text(
                text = stringResource(id = R.string.quantity_plus),
                letterSpacing = 4.sp
            )
        }
    }
}