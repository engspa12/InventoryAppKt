package com.example.dbm.inventoryappkt.presentation.view

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
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
import com.example.dbm.inventoryappkt.presentation.view.screens.MainScreen
import com.example.dbm.inventoryappkt.presentation.view.screens.ProductDetailsScreen
import com.example.dbm.inventoryappkt.presentation.viewmodel.MainViewModel
import com.example.dbm.inventoryappkt.presentation.viewmodel.ProductDetailsViewModel

@Composable
fun App(
    context: Context
){

    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    var showMenu by remember { mutableStateOf(false) }
    val titleTopBar by rememberSaveable { mutableStateOf(context.getString(R.string.app_name)) }
    var navigationType by rememberSaveable { mutableStateOf(Constants.NavType.NAV_MAIN) }
    var saveProductDetails by remember { mutableStateOf(false) }
    var insertDummyProduct by remember { mutableStateOf(false) }
    var loadingScreenContent by remember { mutableStateOf(true) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(
                showMenu = showMenu,
                titleTopBar = titleTopBar,
                navigationType = navigationType,
                goBackToMain = {
                    navController.popBackStack()
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
                loadingScreenContent = loadingScreenContent
            )
        },
        floatingActionButton = {
            if(!loadingScreenContent && navigationType == Constants.NavType.NAV_MAIN){
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.ProductScreen.withArgs(-1))
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

                    navigationType = Constants.NavType.NAV_MAIN
                    val mainViewModel = hiltViewModel<MainViewModel>()

                    MainScreen(
                        navigateToDetailsScreen = { productId ->
                            navController.navigate(Screen.ProductScreen.withArgs(productId))
                        },
                        viewModel = mainViewModel,
                        insertDummyProduct = insertDummyProduct,
                        onInsertDummyProduct = {
                            insertDummyProduct = false
                        },
                        onLoadingMainContent = { loadingContent ->
                            loadingScreenContent = loadingContent
                        }
                    )
                }
                composable(
                    route = Screen.ProductScreen.route + "/{productId}",
                    arguments = listOf(
                        navArgument("productId") {
                            type = NavType.IntType
                            defaultValue = -1
                            nullable = false
                        }
                    )
                ) { backStackEntry ->

                    navigationType = Constants.NavType.NAV_DETAILS
                    val productDetailsViewModel = hiltViewModel<ProductDetailsViewModel>()

                    ProductDetailsScreen(
                        context = context,
                        productId = backStackEntry.arguments?.getInt("productId") ?: -1,
                        viewModel = productDetailsViewModel,
                        saveProductDetails = saveProductDetails,
                        onSavedProduct = {
                            saveProductDetails = false
                        }
                    )
                }
            }
        }
    }
}