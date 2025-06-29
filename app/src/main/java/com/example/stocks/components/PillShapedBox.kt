package com.example.stocks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.stocks.ui.theme.sansFontFamily

@Composable
fun PillShapedBox(text: String) {
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = Color(0xFFDADCE0), // light gray border color
                shape = RoundedCornerShape(24.dp)
            )
            .background(
                color = Color(0xFFF1F3F4),
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Text(
            text = text,
//            fontFamily = sansFontFamily,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 2.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            fontWeight = FontWeight.SemiBold,
        )
    }
}