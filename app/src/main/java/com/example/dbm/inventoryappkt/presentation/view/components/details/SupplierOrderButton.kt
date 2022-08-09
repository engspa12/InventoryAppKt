package com.example.dbm.inventoryappkt.presentation.view.components.details

import androidx.compose.foundation.border
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
import com.example.dbm.inventoryappkt.R

@Composable
fun SupplierOrderButton(
    modifier: Modifier = Modifier
){
    Button(
        onClick = { /*TODO*/ },
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.regular_button_background)
        )
    ) {
        Text(text = stringResource(id = R.string.order_from_supplier))
    }
}