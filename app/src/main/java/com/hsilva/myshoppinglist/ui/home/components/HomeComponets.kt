package com.hsilva.myshoppinglist.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hsilva.myshoppinglist.service.dto.Item

@Composable
fun ShoppingItem(
    shoppingItem: Item,
    onChecked: (Boolean) -> Unit,
    onDelete: (Item) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(checked = shoppingItem.isChecked, onCheckedChange = { onChecked(it) })
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart,
            ) {
                Text(
                    text = shoppingItem.name,
                    style = if (shoppingItem.isChecked) {
                        TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 15.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    } else {
                        TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 15.sp
                        )
                    },
                    color = Color.Black
                )
            }
            IconButton(
                modifier = Modifier.testTag("DeleteButton"),
                onClick = { onDelete(shoppingItem) }) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    }
}