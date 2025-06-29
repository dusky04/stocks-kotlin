package com.example.stocks.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.data.CompanyOverviewData
import com.example.stocks.models.WatchListViewModel
import com.example.stocks.ui.theme.sansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit,
    sheetState: SheetState,
    watchListViewModel: WatchListViewModel,
    stock: CompanyOverviewData,
    availableWatchLists: List<String>,
    onSave: (CompanyOverviewData, List<String>) -> Unit
) {
    var text by remember { mutableStateOf("") }
    val checkedStates = remember(availableWatchLists) {
        mutableStateMapOf<String, Boolean>().apply {
            availableWatchLists.forEach { idx ->
                put(idx, false)
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() }, sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                OutlinedTextField(
                    value = text, modifier = Modifier.weight(1f), onValueChange = {
                        text = it
                    }, label = {
                        Text("New Watchlist", fontFamily = sansFontFamily)
                    }, shape = RoundedCornerShape(12.dp)
                )
                Button(onClick = {
                    if (text.isNotBlank()) {
                        watchListViewModel.addNewWatchList(text)
                        text = ""
                    }
                }) { Text("Add", fontFamily = sansFontFamily) }
            }

            availableWatchLists.forEach { watchListName ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = checkedStates[watchListName] ?: false,
                        onCheckedChange = { isChecked ->
                            checkedStates[watchListName] = isChecked
                        })
                    Text(
                        text = watchListName,
                        fontFamily = sansFontFamily,
                        fontSize = 18.sp
                    )
                }
            }

            Button(
                onClick = {
                    val selected = checkedStates.filter { it.value }.keys.toList()
                    if (selected.isNotEmpty()) {
                        onSave(stock, selected)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Save to Watch Lists", fontSize = 16.sp)
            }
        }
    }
}