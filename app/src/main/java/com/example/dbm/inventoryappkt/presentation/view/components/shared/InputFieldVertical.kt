package com.example.dbm.inventoryappkt.presentation.view.components.shared

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusEventModifier
import androidx.compose.ui.focus.FocusRequesterModifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.inventoryappkt.R

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InputFieldVertical(
    text: String,
    placeholder: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    inputValue: String,
    onInputValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .wrapContentHeight(),
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            fontSize = fontSize,
            fontWeight = fontWeight,
            text = text
        )
        BasicTextField(
            modifier = Modifier
                .wrapContentHeight()
                .onFocusChanged {
                    if(it.isFocused){
                        println("I HAVE focus with $text")
                    } else {
                        println("I DO NOT HAVE focus with $text")
                    }
                },
            maxLines = 1,
            value = inputValue,
            onValueChange = {
                onInputValueChanged(it)
            },
            keyboardOptions = keyboardOptions,
            textStyle = TextStyle(
                color = colorResource(id = R.color.white),
                fontSize = 18.sp,
                textAlign = TextAlign.Start
            ),
            cursorBrush = SolidColor(colorResource(id = R.color.white)),
            decorationBox = { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = inputValue,
                    visualTransformation = VisualTransformation.None,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(text = placeholder)
                    },
                    enabled = true,
                    interactionSource = remember { MutableInteractionSource() },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.add_new_product_screen_background),
                        focusedIndicatorColor = colorResource(id = R.color.white),
                        cursorColor = colorResource(id = R.color.white)
                    ),
                    contentPadding = PaddingValues(0.dp)
                )
            }
        )
    }
}