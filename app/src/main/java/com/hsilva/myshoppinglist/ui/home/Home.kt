package com.hsilva.myshoppinglist.ui.home

import android.view.KeyEvent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hsilva.myshoppinglist.ui.home.components.ShoppingItem

@Composable
fun HomeScreen() {
    val viewModel = viewModel(HomeViewModel::class.java)
    val state by viewModel.state.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val textState = remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth(),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .testTag("TextInput")
                    .onKeyEvent {
                        if (it.nativeKeyEvent.keyCode == KeyEvent.KEYCODE_ENTER) {
                            viewModel.addItem(textState.value.text)
                            textState.value = TextFieldValue("")
                            true
                        }
                        false
                    },
                value = textState.value,
                onValueChange = { textState.value = it },
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.LightGray,
                    disabledBorderColor = Color.LightGray,
                    disabledTextColor = Color.Gray
                ),
                label = {
                    Text(
                        text = "Add new item",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp,
                            color = Color.LightGray
                        )
                    )
                }
            )

            IconButton(modifier = Modifier
                .testTag("AddButton"),
                onClick = {
                    viewModel.addItem(textState.value.text)
                    textState.value = TextFieldValue("")
                }) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
        Spacer(modifier = Modifier.size(16.dp))

        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { viewModel.getAllItems() },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    // Pass the SwipeRefreshState + trigger through
                    state = state,
                    refreshTriggerDistance = trigger,
                    // Enable the scale animation
                    scale = true,
                    // Change the color and shape
                    backgroundColor = Color.Black,
                    shape = MaterialTheme.shapes.small,
                )
            }
        ) {
            if (state.itemList.isNotEmpty()) {
                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier
                        .fillMaxSize()
                        .testTag("ShoppingList")
                ) {
                    items(state.itemList) { item ->
                        ShoppingItem(
                            shoppingItem = item,
                            onChecked = { viewModel.updateItem(item, it) },
                            onDelete = { viewModel.deleteItem(item) }
                        )
                    }
                }
            } else {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Text(
                        text = "You have no items added. \n Try to add a new one. :)",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp,
                            color = Color.LightGray
                        )
                    )
                }
            }
        }
    }

}