package com.example.dbm.inventoryappkt.presentation.view.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.view.components.details.*
import com.example.dbm.inventoryappkt.presentation.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    context: Context,
    productId: Int,
    viewModel: ProductDetailsViewModel,
    saveProductDetails: Boolean,
    onSavedProduct: () -> Unit
){

    var productName by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var manufactureYear by remember { mutableStateOf("") }
    var brand by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var productType by remember { mutableStateOf("") }
    var inStock by remember { mutableStateOf("") }
    var warranty by remember { mutableStateOf("") }

    val listCategoryOptions = listOf(
        stringResource(id = R.string.sports_category),
        stringResource(id = R.string.technology_category),
        stringResource(id = R.string.furniture_category),
        stringResource(id = R.string.clothing_category),
        stringResource(id = R.string.other_category)
    )
    val listStockOptions = listOf(
        stringResource(id = R.string.in_stock_status),
        stringResource(id = R.string.without_stock_status)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.details_screen_background))
            .padding(top = 2.dp, bottom = 12.dp, start = 10.dp, end = 10.dp)
    ) {
        item {
            ImageSelector(
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
                    InputFieldVertical(
                        text = stringResource(id = R.string.description),
                        placeholder = stringResource(id = R.string.description_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        inputValue = productName,
                        onInputValueChanged = {
                            productName = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words,
                            keyboardType = KeyboardType.Text
                        )
                    )
                    InputFieldVertical(
                        text = stringResource(id = R.string.price),
                        placeholder = stringResource(id = R.string.price_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        inputValue = price,
                        onInputValueChanged = {
                            price = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
                    )
                    InputFieldVertical(
                        text = stringResource(id = R.string.manufacture_year),
                        placeholder = stringResource(id = R.string.manufacture_year_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        inputValue = manufactureYear,
                        onInputValueChanged = {
                            manufactureYear = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number
                        )
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
                    InputFieldVertical(
                        text = stringResource(id = R.string.quantity),
                        placeholder = stringResource(id = R.string.quantity_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        inputValue = quantity,
                        onInputValueChanged = {
                            quantity = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words,
                            keyboardType = KeyboardType.Number
                        )
                    )
                    InputFieldVertical(
                        text = stringResource(id = R.string.brand),
                        placeholder = stringResource(id = R.string.brand_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        inputValue = brand,
                        onInputValueChanged = {
                            brand = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words,
                            keyboardType = KeyboardType.Text
                        )
                    )
                    InputFieldVertical(
                        text = stringResource(id = R.string.weight),
                        placeholder = stringResource(id = R.string.weight_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        inputValue = weight,
                        onInputValueChanged = {
                            weight = it
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            capitalization = KeyboardCapitalization.Words,
                            keyboardType = KeyboardType.Number
                        )
                    )
                }
            }
        }
        item {
            SpinnerHorizontal(
                text = stringResource(id = R.string.product_type),
                options = listCategoryOptions,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                itemSelected = productType,
                onItemSelected = {
                    productType = it
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_details_background))
            )
        }
        item {
            SpinnerHorizontal(
                text = stringResource(id = R.string.stock_status),
                options = listStockOptions,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                itemSelected = inStock,
                onItemSelected = {
                    inStock = it
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_details_background))
            )
        }
        item {
            InputFieldHorizontal(
                text = stringResource(id = R.string.warranty),
                placeholder = stringResource(id = R.string.warranty_placeholder),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                inputValue = warranty,
                onInputValueChanged = {
                    warranty = it
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
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
                    modifier = Modifier
                        .weight(2f)
                )
                SupplierOrderButton(
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