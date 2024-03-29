package com.example.dbm.inventoryappkt.presentation.view.screens

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.util.ProductDetailsChangeEvent
import com.example.dbm.inventoryappkt.presentation.util.ValidationEvent
import com.example.dbm.inventoryappkt.presentation.util.mapToStringResource
import com.example.dbm.inventoryappkt.presentation.view.components.add.AddNewProductContent
import com.example.dbm.inventoryappkt.presentation.view.components.shared.ProgressBar
import com.example.dbm.inventoryappkt.presentation.viewmodel.AddNewProductViewModel

@Composable
fun AddNewProductScreen(
    context: Context,
    viewModel: AddNewProductViewModel,
    addNewProduct: Boolean,
    onProductSaved: () -> Unit,
    onErrorOccurred: (String?) -> Unit,
    onContentNotAvailable: (Boolean) -> Unit
) {

    val inputState = viewModel.uiState
    val showProgressBar by viewModel.showProgressBar.collectAsStateWithLifecycle()
    var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            viewModel.onEvent(ProductDetailsChangeEvent.ImageSelectedChanged(imageUri.toString()))
        }

    if (imageUri != null) {
        LaunchedEffect(key1 = imageUri) {
            imageUri?.let {
                bitmap = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }
            }
        }
    }

    if (addNewProduct) {
        LaunchedEffect(key1 = Unit) {
            viewModel.addNewProduct()
        }
    }

    LaunchedEffect(key1 = Unit) {
        viewModel.validationEvent.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    onProductSaved()
                }
                is ValidationEvent.Failure -> {
                    val errorMessage = event.mapToStringResource(context)
                    onErrorOccurred(errorMessage)
                }
            }
        }
    }

    if (showProgressBar == null) {
        onContentNotAvailable(false)
        AddNewProductContent(
            bitmap = bitmap,
            inputState = inputState,
            onChangeEvent = { event ->
                viewModel.onEvent(event)
            },
            onSelectImageButtonClicked = {
                launcher.launch("image/*")
            }
        )
    } else {
        onContentNotAvailable(true)
        ProgressBar(
            message = stringResource(id = R.string.loading_adding_product),
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }

}