package com.example.dbm.inventoryappkt.presentation.view.screens

import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.util.ValidationEvent
import com.example.dbm.inventoryappkt.presentation.view.components.add.AddNewProductContent
import com.example.dbm.inventoryappkt.presentation.viewmodel.AddNewProductViewModel
import com.example.dbm.inventoryappkt.util.StringWrapper

@Composable
fun AddNewProductScreen(
    context: Context,
    viewModel: AddNewProductViewModel,
    addNewProduct: Boolean,
    onProductSaved: () -> Unit,
    onErrorOccurred: (String?) -> Unit,
    onLoadingContent: (Boolean) -> Unit
) {

    val inputState = viewModel.uiState

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
                    val args = event.errorMessage?.getStringArgs()
                    val errorMessage = context.getString(event.errorMessage?.getStringIdResource() ?: 0, if(args != null && args.isNotEmpty()) args[0] else "")
                    onErrorOccurred(errorMessage)
                }
            }
        }
    }

    AddNewProductContent(
        inputState = inputState,
        onChangeEvent = { event ->
            viewModel.onEvent(event)
        },
        onSelectImageButtonClicked = {
            //TODO: SELECT IMAGE GALLERY
        }
    )
}