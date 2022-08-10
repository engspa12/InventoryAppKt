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
            .background(color = colorResource(id = R.color.item_list_background)),
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
                contentScale = ContentScale.FillBounds,
                modifier = modifier
                    .clickable {
                        onItemClicked()
                    }
            )
        }
        Spacer(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .fillMaxHeight()
                .requiredWidth(2.dp)
                .background(color = MaterialTheme.colors.onPrimary)
                .clickable {
                    onItemClicked()
                }
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 10.dp)
                .weight(1f)
                .clickable {
                    onItemClicked()
                },
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "Name: ${listItem.productName}")
            Text(text = "Quantity: ${listItem.productQuantity}")
            Text(text = "Price: ${listItem.productPrice}")
        }
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxHeight()
                .background(color = colorResource(id = R.color.new_sale_button_background))
                .padding(horizontal = 10.dp)
                .clickable {
                    onNewSaleClicked()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "New Sale")
        }
    }
}