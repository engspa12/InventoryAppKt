package com.example.dbm.inventoryappkt.presentation.view.components.details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.model.ProductDetailsView
import com.example.dbm.inventoryappkt.presentation.util.mapToStringResource

@Composable
fun ProductDetailsContent(
    item: ProductDetailsView,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit,
    onRequestToSupplier: (ProductDetailsView) -> Unit,
    onDeleteProduct: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.details_screen_background))
            .padding(top = 2.dp, bottom = 12.dp, start = 10.dp, end = 10.dp)
    ) {
        item {
            ProductRemover(
                isDummyProduct = item.isDummyProduct,
                productUrlImage = item.productImageUrl,
                onDeleteProductClicked = {
                     onDeleteProduct()
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(120.dp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .height(IntrinsicSize.Max)
                    .background(color = colorResource(id = R.color.item_details_background))
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    FieldVertical(
                        title = stringResource(id = R.string.name),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        value = item.productName
                    )
                    FieldVertical(
                        title = stringResource(id = R.string.price),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        value = String.format("%.2f", item.productPrice)
                    )
                    FieldVertical(
                        title = stringResource(id = R.string.manufacture_year),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        value = String.format("%d", item.productManufactureYear)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 10.dp)
                        .fillMaxHeight()
                        .requiredWidth(2.dp)
                        .background(color = MaterialTheme.colors.onPrimary)
                )
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    FieldVertical(
                        title = stringResource(id = R.string.quantity),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        value = String.format("%d", item.productQuantity)
                    )
                    FieldVertical(
                        title = stringResource(id = R.string.brand),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        value = item.productBrand
                    )
                    FieldVertical(
                        title = stringResource(id = R.string.weight),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        value = String.format("%.2f", item.productWeight)
                    )
                }
            }
        }
        item {
            FieldHorizontal(
                title = stringResource(id = R.string.product_type),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                value = stringResource(id = item.productType.mapToStringResource()) ,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_details_background))
            )
        }
        item {
            FieldHorizontal(
                title = stringResource(id = R.string.stock_status),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                value = stringResource(id = item.productInStock.mapToStringResource()),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_details_background))
            )
        }
        item {
            FieldHorizontal(
                title = stringResource(id = R.string.warranty),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                value = String.format("%d", item.productWarranty),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_details_background))
            )
        }
        item {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(80.dp)
                    .background(color = colorResource(id = R.color.item_details_background))
            ) {
                QuantityEditor(
                    onIncreaseQuantity = {
                       onIncreaseQuantity()
                    },
                    onDecreaseQuantity = {
                       onDecreaseQuantity()
                    },
                    modifier = Modifier
                        .weight(2f)
                )
                SupplierOrderButton(
                    onRequestToSupplier = {
                       onRequestToSupplier(item)
                    },
                    modifier = Modifier
                        .padding(start = 5.dp, end = 10.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colors.onPrimary,
                            shape = RoundedCornerShape(18.dp)
                        )
                        .height(50.dp)
                        .weight(3f)
                )
            }
        }
    }
}