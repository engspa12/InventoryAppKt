package com.example.dbm.inventoryappkt.global

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {
    const val STORAGE_FOLDER = "inventory_photos"
    const val INSERT_DUMMY_PRODUCT = "insert_dummy_product"
    const val MAX_QUANTITY_ALLOWED = 50
    const val MIN_QUANTITY_ALLOWED = 1
    val DATASTORE_KEY_USER_ID = stringPreferencesKey("user_id")

    enum class NavType {
        NAV_MAIN,
        NAV_DETAILS
    }
}