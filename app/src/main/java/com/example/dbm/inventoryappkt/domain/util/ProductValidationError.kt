package com.example.dbm.inventoryappkt.domain.util

enum class ProductValidationError {
    NONE,
    MUST_SELECT_IMAGE_FOR_PRODUCT,
    TYPE_CANNOT_BE_EMPTY,
    STOCK_STATUS_CANNOT_BE_EMPTY,
    QUANTITY_MUST_BE_WHOLE_NUMBER,
    QUANTITY_CANNOT_BE_NEGATIVE,
    WEIGHT_MUST_BE_DECIMAL_NUMBER,
    WEIGHT_CANNOT_BE_NEGATIVE,
    WARRANTY_MUST_BE_WHOLE_NUMBER,
    WARRANTY_CANNOT_BE_NEGATIVE,
    BRAND_CANNOT_BE_EMPTY,
    BRAND_CANNOT_BE_LESS_THAN_THREE_CHARACTERS,
    PRICE_MUST_BE_DECIMAL_NUMBER,
    PRICE_CANNOT_BE_NEGATIVE,
    MANUFACTURE_YEAR_MUST_BE_WHOLE_NUMBER,
    MANUFACTURE_YEAR_MUST_HAVE_FOUR_DIGITS,
    MANUFACTURE_YEAR_OUT_OF_RANGE,
    NAME_CANNOT_BE_EMPTY,
    NAME_CANNOT_BE_LESS_THAN_THREE_CHARACTERS
}