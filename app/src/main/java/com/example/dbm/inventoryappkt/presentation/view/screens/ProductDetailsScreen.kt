package com.example.dbm.inventoryappkt.presentation.view.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.state.ProductDetailsState
import com.example.dbm.inventoryappkt.presentation.util.ProductDetailsActionEvent
import com.example.dbm.inventoryappkt.presentation.util.mapToStringResource
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
    onProductDeleted: () -> Unit,
    onErrorOccurred: (String?) -> Unit,
    onContentNotAvailable: (Boolean) -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    if(saveProductDetails) {
        LaunchedEffect(key1 = Unit) {
            viewModel.updateProduct(productId)
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.getProductDetails(productId)

        viewModel.productActionEvent.collect { event ->
            when(event) {
                is ProductDetailsActionEvent.ProductDetailsUpdated -> {
                    onProductSaved()
                }
                is ProductDetailsActionEvent.ProductDetailsDeleted -> {
                    onProductDeleted()
                }
                is ProductDetailsActionEvent.GenericError, ProductDetailsActionEvent.DeletingFromStorageError,
                ProductDetailsActionEvent.NoConnectionError, ProductDetailsActionEvent.NoAuthenticatedError -> {
                    val errorMessage = context.getString(event.mapToStringResource())
                    onErrorOccurred(errorMessage)
                }
            }
        }
    }

    when(uiState) {
        is ProductDetailsState.Success -> {
            onContentNotAvailable(false)
            uiState.value?.let { productDetailsView ->
                ProductDetailsContent(
                    item = productDetailsView,
                    onIncreaseQuantity = {
                        viewModel.increaseProductQuantity()
                    },
                    onDecreaseQuantity = {
                        viewModel.decreaseProductQuantity()
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
                    },
                    onDeleteProduct = {
                        viewModel.deleteProduct(productDetailsView.productId)
                    }
                )
            }
        }
        is ProductDetailsState.Loading -> {
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