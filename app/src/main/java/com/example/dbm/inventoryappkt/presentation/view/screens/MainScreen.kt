package com.example.dbm.inventoryappkt.presentation.view.screens

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.dbm.inventoryappkt.presentation.state.MainState
import com.example.dbm.inventoryappkt.presentation.util.MainEvent
import com.example.dbm.inventoryappkt.presentation.view.components.main.ProductsList
import com.example.dbm.inventoryappkt.presentation.view.components.shared.ProgressBar
import com.example.dbm.inventoryappkt.presentation.viewmodel.MainViewModel

@Composable
fun MainScreen(
    context: Context,
    navigateToDetailsScreen: (Int) -> Unit,
    viewModel: MainViewModel,
    insertDummyProduct: Boolean,
    onDummyProductInserted: () -> Unit,
    onErrorOccurred: (String?) -> Unit,
    onContentNotAvailable: (Boolean) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    if(insertDummyProduct) {
        LaunchedEffect(key1 = Unit) {
            viewModel.insertDummyProduct()
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getProducts()

        viewModel.mainEvent.collect { event ->
            when(event) {
                is MainEvent.ListChanged -> {
                    if(event.listHasItems){
                        onDummyProductInserted()
                    }
                }
                is MainEvent.Error -> {
                    val args = event.errorMessage.getStringArgs()
                    val errorMessage = context.getString(event.errorMessage.getStringIdResource() ?: 0, if(args.isNotEmpty()) args[0] else "")
                    onErrorOccurred(errorMessage)
                }
            }

        }
    }

    when(uiState) {
        is MainState.Success -> {
            onContentNotAvailable(false)
            ProductsList(
                lazyListState = lazyListState,
                list = uiState.value,
                onItemClicked = { productId ->
                    navigateToDetailsScreen(productId)
                },
                onItemNewSale = { productId ->
                    viewModel.updateProductQuantity(productId)
                },
                onItemSwiped = { productId ->
                    viewModel.deleteProduct(productId)
                }
            )
        }
        is MainState.Loading -> {
            onContentNotAvailable(true)
            ProgressBar(
                message = uiState.loadingMessage.asString(),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}