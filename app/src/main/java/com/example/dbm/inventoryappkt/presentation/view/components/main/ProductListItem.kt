package com.example.dbm.inventoryappkt.presentation.view.components.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProductListItem(
    listItem: ProductListView,
    onItemClicked: () -> Unit,
    onNewSaleClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .padding(2.dp)
            .background(color = colorResource(id = R.color.item_list_background))
            .clickable {
                onItemClicked()
            },
        verticalAlignment = Alignment.CenterVertically
    ){
        if(listItem.isDummyProduct) {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .requiredSize(70.dp),
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.clothing),
                contentDescription = stringResource(id = R.string.dummy_image_content_description)
            )
        } else {
            GlideImage(
                modifier = Modifier
                    .padding(10.dp)
                    .requiredSize(70.dp),
                alignment = Alignment.Center,
                imageModel = listItem.productUrlImage,
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
                contentScale = ContentScale.FillBounds
            )
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxHeight()
                .requiredWidth(2.dp)
                .background(color = MaterialTheme.colors.onPrimary)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 10.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.name_list_item, listItem.productName),
                color = MaterialTheme.colors.onPrimary,
            )
            Text(
                text = stringResource(id = R.string.quantity_list_item, listItem.productQuantity),
                color = MaterialTheme.colors.onPrimary,
            )
            Text(
                text = stringResource(id = R.string.price_list_item,  listItem.productPrice),
                color = MaterialTheme.colors.onPrimary,
            )
        }
        Box(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxHeight()
                .background(color = colorResource(id = R.color.new_sale_button_background))
                .clickable {
                    onNewSaleClicked()
                }
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.new_sale_button_text),
                color = MaterialTheme.colors.onPrimary,
            )
        }
    }
}