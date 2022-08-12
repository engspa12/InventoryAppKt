package com.example.dbm.inventoryappkt.presentation.view.components.add

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.example.dbm.inventoryappkt.presentation.state.ProductInputState
import com.example.dbm.inventoryappkt.presentation.util.ProductDetailsChangeEvent

@Composable
fun AddNewProductContent(
    inputState: ProductInputState,
    onChangeEvent: (ProductDetailsChangeEvent) -> Unit,
    onSelectImageButtonClicked: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.add_new_product_screen_background))
            .padding(top = 2.dp, bottom = 12.dp, start = 10.dp, end = 10.dp)
    ) {
        item {
            ImageSelector(
                onSelectImageButtonClicked = {
                    onSelectImageButtonClicked()
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
                    .background(color = colorResource(id = R.color.item_add_new_product_background))
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 10.dp),
                    horizontalAlignment = Alignment.Start,
                ) {
                    InputFieldVertical(
                        text = stringResource(id = R.string.name),
                        placeholder = stringResource(id = R.string.name_placeholder),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        inputValue = inputState.productName,
                        onInputValueChanged = {
                            onChangeEvent(ProductDetailsChangeEvent.NameChanged(it))
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
                        inputValue = inputState.productPrice,
                        onInputValueChanged = {
                            onChangeEvent(ProductDetailsChangeEvent.PriceChanged(it))
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
                        inputValue = inputState.productManufactureYear,
                        onInputValueChanged = {
                            onChangeEvent(ProductDetailsChangeEvent.ManufactureYearChanged(it))
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
                        inputValue = inputState.productQuantity,
                        onInputValueChanged = {
                            onChangeEvent(ProductDetailsChangeEvent.QuantityChanged(it))
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
                        inputValue = inputState.productBrand,
                        onInputValueChanged = {
                            onChangeEvent(ProductDetailsChangeEvent.BrandChanged(it))
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
                        inputValue = inputState.productWeight,
                        onInputValueChanged = {
                            onChangeEvent(ProductDetailsChangeEvent.WeightChanged(it))
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
                options = inputState.categoryOptions,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                itemSelected = inputState.productTypeText,
                onItemSelected = {
                    onChangeEvent(ProductDetailsChangeEvent.TypeChanged(it))
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_add_new_product_background))
            )
        }
        item {
            SpinnerHorizontal(
                text = stringResource(id = R.string.stock_status),
                options = inputState.stockStatusOptions,
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                itemSelected = inputState.productStockStatusText,
                onItemSelected = {
                    onChangeEvent(ProductDetailsChangeEvent.StockStatusChanged(it))
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_add_new_product_background))
            )
        }
        item {
            InputFieldHorizontal(
                text = stringResource(id = R.string.warranty),
                placeholder = stringResource(id = R.string.warranty_placeholder),
                fontSize = 18.sp,
                fontWeight = FontWeight.W600,
                inputValue = inputState.productWarranty,
                onInputValueChanged = {
                    onChangeEvent(ProductDetailsChangeEvent.WarrantyChanged(it))
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                    .requiredHeight(60.dp)
                    .background(color = colorResource(id = R.color.item_add_new_product_background))
            )
        }
    }
}