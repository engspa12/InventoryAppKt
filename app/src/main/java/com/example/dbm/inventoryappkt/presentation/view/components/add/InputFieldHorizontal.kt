package com.example.dbm.inventoryappkt.presentation.view.components.add

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.inventoryappkt.R

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun InputFieldHorizontal(
    text: String,
    placeholder: String,
    fontSize: TextUnit,
    fontWeight: FontWeight,
    inputValue: String,
    onInputValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
){
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        modifier = modifier
            .padding(start =  10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = fontWeight,
            modifier = Modifier
                .padding(end = 10.dp)
        )
        BasicTextField(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
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
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
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
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colors.onPrimary,
                        )
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