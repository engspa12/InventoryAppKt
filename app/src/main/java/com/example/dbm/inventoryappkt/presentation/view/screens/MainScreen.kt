package com.example.dbm.inventoryappkt.presentation.view.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.inventoryappkt.presentation.state.MainState
import com.example.dbm.inventoryappkt.presentation.view.components.main.ProductsList
import com.example.dbm.inventoryappkt.presentation.view.components.shared.ErrorIndicator
import com.example.dbm.inventoryappkt.presentation.view.components.shared.ProgressBar
import com.example.dbm.inventoryappkt.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
    navigateToDetailsScreen: (Int) -> Unit,
    viewModel: MainViewModel,
    insertDummyProduct: Boolean,
    onInsertDummyProduct: () -> Unit,
    onLoadingMainContent: (Boolean) -> Unit
){
    val uiState by viewModel.uiState.collectAsState()
    val lazyListState = rememberLazyListState()

    if(insertDummyProduct) {
        LaunchedEffect(key1 = Unit) {
            viewModel.insertDummyProduct()
            delay(1200L)
            onInsertDummyProduct()
        }
    } else {
        LaunchedEffect(key1 = Unit) {
            viewModel.getProducts()
        }
    }

    when(uiState) {
        is MainState.Success -> {
            onLoadingMainContent(false)
            ProductsList(
                lazyListState = lazyListState,
                list = uiState.value,
                onItemClicked = { productId ->
                    navigateToDetailsScreen(productId)
                },
                onItemNewSale = { productId ->
                    viewModel.updateProductQuantity(productId)
                }
            )
        }
        is MainState.Error -> {
            onLoadingMainContent(false)
            ErrorIndicator(
                errorMessage = uiState.errorMessage.asString(),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        }
        is MainState.Loading -> {
            onLoadingMainContent(true)
            ProgressBar(
                message = uiState.loadingMessage.asString(),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }
}