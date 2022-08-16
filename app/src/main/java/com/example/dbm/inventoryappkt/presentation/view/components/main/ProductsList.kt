package com.example.dbm.inventoryappkt.presentation.view.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dbm.inventoryappkt.R
import com.example.dbm.inventoryappkt.presentation.model.ProductListView
import com.example.dbm.inventoryappkt.presentation.view.components.shared.ErrorIndicator

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductsList(
    lazyListState: LazyListState,
    list: List<ProductListView>?,
    onItemClicked: (Int) -> Unit,
    onItemNewSale: (Int) -> Unit,
    onItemSwiped: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    if(list != null){
        if(list.isEmpty()){
            ErrorIndicator(
                errorMessage = stringResource(id = R.string.empty_list_message),
                color = MaterialTheme.colors.onPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.W600,
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.main_screen_empty_background))
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(horizontal = 20.dp)
            )
        } else {
            LazyColumn(
                state = lazyListState,
                modifier = modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.main_screen_full_background))
                    .padding(top = 2.dp, bottom = 12.dp, start = 10.dp, end = 10.dp)
            ){
                itemsIndexed(list, key = { _ , listItem ->
                    listItem.productId
                }) { _ , listItem ->

                    val dismissState = rememberDismissState(
                        confirmStateChange = {
                            if(it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart){
                                onItemSwiped(listItem.productId)
                            }
                            true
                        }
                    )

                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.StartToEnd, DismissDirection.EndToStart),
                        dismissThresholds = {
                             FractionalThreshold(0.5f)
                        },
                        background = {

                        },
                        dismissContent = {
                            ProductListItem(
                                listItem,
                                onItemClicked = {
                                    onItemClicked(listItem.productId)
                                },
                                onNewSaleClicked = {
                                    onItemNewSale(listItem.productId)
                                },
                                modifier = Modifier
                                    .padding(top = 10.dp)
                                    .border(width = 2.dp, color = MaterialTheme.colors.onPrimary)
                                    .requiredHeight(height = 120.dp)
                            )

                        }
                    )
                }
            }
        }
    } else {
        ErrorIndicator(
            errorMessage = stringResource(id = R.string.error_retrieving_data),
            color = MaterialTheme.colors.onPrimary,
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            modifier = modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.main_screen_empty_background))
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = 20.dp)
        )
    }
}