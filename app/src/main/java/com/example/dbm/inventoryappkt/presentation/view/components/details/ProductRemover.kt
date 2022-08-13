package com.example.dbm.inventoryappkt.presentation.view.components.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.dbm.inventoryappkt.R
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductRemover(
    isDummyProduct: Boolean,
    productUrlImage: String,
    onDeleteProductClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(color = colorResource(id = R.color.item_details_background)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if(isDummyProduct){
            Image(
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 20.dp),
                painter = painterResource(id = R.drawable.clothing),
                contentDescription = stringResource(id = R.string.clothing_category)
            )
        } else {
            GlideImage(
                imageModel = productUrlImage,
                requestOptions = {
                    RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .centerCrop()
                },
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colors.background,
                    highlightColor = Color.Green,
                    durationMillis = 350,
                    dropOff = 0.65f,
                    tilt = 20f
                ),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(vertical = 20.dp)
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 20.dp)
            )
        }
        Button(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .border(width = 2.dp, color = MaterialTheme.colors.onPrimary, shape = RoundedCornerShape(18.dp))
                .height(50.dp)
                .weight(3f),
            onClick = {
                onDeleteProductClicked()
            },
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.regular_button_background)
            )
        ) {
            Text(
                text = stringResource(id = R.string.delete_product_button_text),
                color = MaterialTheme.colors.onPrimary,
            )
        }
    }
}