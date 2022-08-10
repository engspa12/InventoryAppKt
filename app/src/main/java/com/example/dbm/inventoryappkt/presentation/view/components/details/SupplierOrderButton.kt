package com.example.dbm.inventoryappkt.presentation.view.components.details

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dbm.inventoryappkt.R

@Composable
fun SupplierOrderButton(
    onRequestToSupplier:() -> Unit,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {
            onRequestToSupplier()
                  },
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.regular_button_background)
        )
    ) {
        Text(text = stringResource(id = R.string.order_from_supplier))
    }
}