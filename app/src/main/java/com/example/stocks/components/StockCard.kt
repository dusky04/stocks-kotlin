package com.example.stocks.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.LocalNavController
import com.example.stocks.assets.TrendingDownIcon
import com.example.stocks.assets.TrendingUpIcon
import com.example.stocks.data.TopGainerLoser
import com.example.stocks.ui.theme.sansFontFamily


@Composable
fun StockCard(cardInfo: TopGainerLoser, isGainer: Boolean) {
    val navController = LocalNavController.current
    val iconColor = if (isGainer) Color(0xFF4CAF50) else Color(0xFFF44336)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant,
                shape = RoundedCornerShape(8.dp)
            ),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            navController.navigate("overview/${cardInfo.ticker}")
        }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isGainer) Color(0xffE6F4EA) else Color(0xFFFCE8E6)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (isGainer) TrendingUpIcon else TrendingDownIcon,
                        contentDescription = null,
                        tint = if (isGainer) Color(0xFF4CAF50) else Color(0xFFF44336),
                        modifier = Modifier.size(24.dp)
                    )
                }
                Column {
                    Text(
                        text = cardInfo.ticker ?: "",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.SemiBold, fontSize = 12.sp
                        ),
                        fontFamily = sansFontFamily
                    )
                    Text(
                        text = cardInfo.price?.toFloatOrNull()?.let { "$%.2f".format(it) } ?: "N/A",
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
                        fontFamily = sansFontFamily,
                    )
                }
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(text = cardInfo.changePercentage?.dropLast(1)?.toFloatOrNull()?.let {
                    "%.2f".format(it) + "%"
                } ?: "N/A",
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontWeight = FontWeight.Medium, fontSize = 12.sp
                    ),
                    fontFamily = sansFontFamily,
                    color = iconColor)
                Text(
                    text =
                    cardInfo.changeAmount?.toFloatOrNull()?.let { "$%.2f".format(it) }
                        ?: "N/A", style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp
                ), fontFamily = sansFontFamily, color = iconColor)
            }
        }
    }
}