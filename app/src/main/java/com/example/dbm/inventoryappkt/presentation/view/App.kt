package com.example.dbm.inventoryappkt.presentation.view

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.global.Constants
import com.example.dbm.inventoryappkt.presentation.navigation.Screen
import com.example.dbm.inventoryappkt.presentation.view.components.shared.TopBar
import com.example.dbm.inventoryappkt.presentation.view.screens.AddNewProductScreen
import com.example.dbm.inventoryappkt.presentation.view.screens.MainScreen
import com.example.dbm.inventoryappkt.presentation.view.screens.ProductDetailsScreen
import com.example.dbm.inventoryappkt.presentation.viewmodel.AddNewProductViewModel
import com.example.dbm.inventoryappkt.presentation.viewmodel.MainViewModel
import com.example.dbm.inventoryappkt.presentation.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun App(
    context: Context
){

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current
    var showMenu by remember { mutableStateOf(false) }
    var titleTopBar by rememberSaveable { mutableStateOf(context.getString(R.string.app_name)) }
    var navigationType by rememberSaveable { mutableStateOf(Constants.NavType.NAV_MAIN) }
    var saveProductDetails by remember { mutableStateOf(false) }
    var insertDummyProduct by remember { mutableStateOf(false) }
    var screenContentAvailable by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                Snackbar(
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = MaterialTheme.colors.onBackground,
                    snackbarData = data
                )
            }
        },
        topBar = {
            TopBar(
                showMenu = showMenu,
                titleTopBar = titleTopBar,
                navigationType = navigationType,
                goBackToMain = {
                    navController.popBackStack()
                    scaffoldState.snackbarHostState
                        .currentSnackbarData?.dismiss()
                },
                onDismissMenu = {
                    showMenu = false
                },
                onMenuIconClick = {
                    showMenu = !showMenu
                },
                onMenuItemClick = {
                    showMenu = false
                    insertDummyProduct = true
                },
                saveProductDetails = {
                    saveProductDetails = true
                },
                loadingScreenContent = screenContentAvailable
            )
        },
        floatingActionButton = {
            if(!screenContentAvailable && navigationType == Constants.NavType.NAV_MAIN){
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.AddNewProductScreen.route)
                    },
                    backgroundColor = colorResource(id = R.color.regular_button_background)
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        tint = MaterialTheme.colors.onPrimary,
                        contentDescription = stringResource(id = R.string.add_product_content_description)
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController, startDestination = "inventory", modifier = Modifier.padding(paddingValues)){
            navigation(startDestination = Screen.MainScreen.route, route = "inventory") {
                composable(
                    route = Screen.MainScreen.route
                ) {

                    titleTopBar = stringResource(id = R.string.app_name)
                    navigationType = Constants.NavType.NAV_MAIN
                    val mainViewModel = hiltViewModel<MainViewModel>()

                    MainScreen(
                        context = context,
                        navigateToDetailsScreen = { productId ->
                            navController.navigate(Screen.ProductDetailsScreen.withArgs(productId))
                        },
                        viewModel = mainViewModel,
                        insertDummyProduct = insertDummyProduct,
                        onDummyProductInserted = {
                            insertDummyProduct = false
                        },
                        onErrorOccurred = { errorMessage ->
                            keyboardController?.hide()
                            insertDummyProduct = false
                            if(errorMessage != null){
                                coroutineScope.launch {

                                    scaffoldState.snackbarHostState
                                        .currentSnackbarData?.dismiss()

                                    val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(
                                        message = errorMessage,
                                        actionLabel = context.getString(R.string.dismiss_snackbar),
                                        duration = SnackbarDuration.Indefinite
                                    )

                                    when(snackbarResult) {
                                        SnackbarResult.ActionPerformed -> {
                                            scaffoldState.snackbarHostState
                                                .currentSnackbarData?.dismiss()
                                        }
                                        else -> {}
                                    }
                                }
                            }
                        },
                        onContentNotAvailable = { contentAvailable ->
                            screenContentAvailable = contentAvailable
                        }
                    )
                }
                composable(
                    route = Screen.ProductDetailsScreen.route + "/{productId}",
                    arguments = listOf(
                        navArgument("productId") {
                            type = NavType.IntType
                            defaultValue = -1
                            nullable = false
                        }
                    )
                ) { backStackEntry ->

                    titleTopBar = stringResource(id = R.string.product_details_title)
                    navigationType = Constants.NavType.NAV_DETAILS
                    val productDetailsViewModel = hiltViewModel<ProductDetailsViewModel>()

                    ProductDetailsScreen(
                        context = context,
                        productId = backStackEntry.arguments?.getInt("productId") ?: -1,
                        viewModel = productDetailsViewModel,
                        saveProductDetails = saveProductDetails,
                        onProductSaved  = {
                            saveProductDetails = false
                            navController.popBackStack()
                        },
                        onProductDeleted = {
                            saveProductDetails = false
                            navController.popBackStack()
                        },
                        onErrorOccurred = { errorMessage ->
                            keyboardController?.hide()
                            saveProductDetails = false
                            if(errorMessage != null){
                                coroutineScope.launch {

                                    scaffoldState.snackbarHostState
                                        .currentSnackbarData?.dismiss()

                                    scaffoldState.snackbarHostState.showSnackbar(
                                        errorMessage
                                    )
                                }
                            }
                        },
                        onContentNotAvailable = { contentAvailable ->
                            screenContentAvailable = contentAvailable
                        }
                    )
                }
                composable(
                    route = Screen.AddNewProductScreen.route
                ) {

                    titleTopBar = stringResource(id = R.string.add_product_content_description)
                    navigationType = Constants.NavType.NAV_DETAILS
                    val addNewProductViewModel = hiltViewModel<AddNewProductViewModel>()

                    AddNewProductScreen(
                        context = context,
                        viewModel = addNewProductViewModel,
                        addNewProduct = saveProductDetails,
                        onProductSaved  = {
                            keyboardController?.hide()
                            saveProductDetails = false
                            navController.popBackStack()
                        },
                        onErrorOccurred = { errorMessage ->
                            keyboardController?.hide()
                            saveProductDetails = false
                            if(errorMessage != null){
                                coroutineScope.launch {

                                    scaffoldState.snackbarHostState
                                        .currentSnackbarData?.dismiss()

                                    scaffoldState.snackbarHostState.showSnackbar(
                                        errorMessage
                                    )
                                }
                            }
                        },
                        onContentNotAvailable = { contentAvailable ->
                            screenContentAvailable = contentAvailable
                        }
                    )
                }
            }
        }
    }
}