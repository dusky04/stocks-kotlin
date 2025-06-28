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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.ui.theme.sansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    onDismiss: () -> Unit, sheetState: SheetState
) {
    var text by remember { mutableStateOf("") }
    var check by remember { mutableStateOf(false) }
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
                    value = text,
                    modifier = Modifier.weight(1f),
                    onValueChange = {
                        text = it
                    },
                    label = {
                        Text("New Watchlist", fontFamily = sansFontFamily)
                    },
                    shape = RoundedCornerShape(12.dp)
                )
                Button(onClick = {}) { Text("Add", fontFamily = sansFontFamily) }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = check, onCheckedChange = {
                    check = !check
                })
                Text(
                    text = "Watchlist 1",
                    fontFamily = sansFontFamily,
                    fontSize = 18.sp
                )
            }
        }
    }
}