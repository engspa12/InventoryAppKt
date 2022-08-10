package com.example.dbm.inventoryappkt.presentation.view.screens

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.util.ValidationEvent
import com.example.dbm.inventoryappkt.presentation.view.components.add.AddNewProductContent
import com.example.dbm.inventoryappkt.presentation.viewmodel.AddNewProductViewModel
import kotlinx.coroutines.delay

@Composable
fun AddNewProductScreen(
    context: Context,
    viewModel: AddNewProductViewModel,
    addNewProduct: Boolean,
    onProductSaved: () -> Unit,
    onErrorOccurred: () -> Unit,
    onLoadingContent: (Boolean) -> Unit
) {

    if(addNewProduct){
        LaunchedEffect(key1 = Unit) {
            onLoadingContent(true)
            viewModel.addNewProduct()
        }
    }
    
    LaunchedEffect(key1 = Unit){
        viewModel.validationEvent.collect { event ->
            onLoadingContent(false)
            when(event) {
                is ValidationEvent.Success -> {
                    onProductSaved()
                }
                is ValidationEvent.Failure -> {
                    onErrorOccurred()
                }
            }
        }
    }

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

    AddNewProductContent(
        inputState = viewModel.uiState,
        onChangeEvent = { event ->
            viewModel.onEvent(event)
        },
        onSelectImageButtonClicked = {
            //TODO: SELECT IMAGE GALLERY
        },
        listStockOptions = listStockOptions,
        listCategoryOptions = listCategoryOptions
    )
}