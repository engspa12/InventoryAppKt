package com.example.dbm.inventoryappkt.presentation.navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("main_screen")
    object ProductDetailsScreen: Screen("product_details_screen")
    object AddNewProductScreen: Screen("add_new_product_screen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}