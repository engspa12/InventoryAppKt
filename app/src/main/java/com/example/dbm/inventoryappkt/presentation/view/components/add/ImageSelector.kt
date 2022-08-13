package com.example.dbm.inventoryappkt.presentation.view.components.add

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dbm.inventoryappkt.R

@Composable
fun ImageSelector(
    //imageUri: Uri?,
    bitmap: Bitmap?,
    onSelectImageButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .background(color = colorResource(id = R.color.item_add_new_product_background)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(bitmap != null) {
            Image(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 20.dp),
                bitmap = bitmap.asImageBitmap(),
                contentDescription = stringResource(id = R.string.select_image_content_description)
            )
        } else {
            Image(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 20.dp),
                painter = painterResource(id = R.drawable.select_image),
                contentDescription = stringResource(id = R.string.select_image_content_description)
            )
        }
        Button(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colors.onPrimary,
                    shape = RoundedCornerShape(18.dp)
                )
                .height(50.dp)
                .weight(3f),
            onClick = {
                onSelectImageButtonClicked()
            },
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.regular_button_background)
            )
        ) {
            Text(
                text = stringResource(id = R.string.select_image_button_text),
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}