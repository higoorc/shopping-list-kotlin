package com.hsilva.myshoppinglist.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.hsilva.myshoppinglist.service.dto.Item

@Composable
fun ShoppingItem(
    shoppingItem: Item,
    onChecked: (Boolean) -> Unit,
    onDelete: (Item) -> Unit
) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(checked = shoppingItem.isChecked, onCheckedChange = { onChecked(it) })
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    text = shoppingItem.name,
                    style = MaterialTheme.typography.subtitle2,
                    color = Color.Black,
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