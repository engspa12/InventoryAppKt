package com.example.dbm.inventoryappkt.presentation.view.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.example.dbm.inventoryappkt.presentation.state.ProductDetailsState
import com.example.dbm.inventoryappkt.presentation.view.components.details.*
import com.example.dbm.inventoryappkt.presentation.view.components.shared.*
import com.example.dbm.inventoryappkt.presentation.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    context: Context,
    productId: Int,
    viewModel: ProductDetailsViewModel,
    saveProductDetails: Boolean,
    onProductSaved: () -> Unit,
    onErrorOccurred: () -> Unit,
    onLoadingContent: (Boolean) -> Unit
) {

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getProductDetails(productId)
    }

    when(uiState) {
        is ProductDetailsState.Success -> {
            onLoadingContent(false)
            uiState.value?.let {
                ProductDetailsContent(
                    item = it,
                    onIncreaseQuantity = {

                    },
                    onDecreaseQuantity = {

                    },
                    onRequestToSupplier = { productDetails ->
                        val quantity = productDetails.productQuantity
                        val addresses = Array(1) {
                            "arturo.lpc12@gmail.com"
                        }
                        val brand = productDetails.productBrand
                        val name = productDetails.productName
                        val price = productDetails.productPrice

                        val currencyAbbreviation = context.getString(R.string.currency_abbreviation)
                        val message = context.getString(R.string.message_email, quantity, name, brand, price, currencyAbbreviation)

                        val intent = Intent(Intent.ACTION_SENDTO)
                        intent.data = Uri.parse("mailto:")
                        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
                        intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.subject_email))
                        intent.putExtra(Intent.EXTRA_TEXT, message)

                        if (intent.resolveActivity(context.packageManager) != null) {
                            context.startActivity(intent)
                        }
                    }) {
                }
            }
        }
        is ProductDetailsState.Loading -> {
            onLoadingContent(false)
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
        is ProductDetailsState.Error -> {
            onLoadingContent(true)
            ProgressBar(
                message = uiState.loadingMessage.asString(),
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.CenterVertically)
            )
        }
    }

}