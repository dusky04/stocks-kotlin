package com.example.stocks.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocks.LocalNavController
import com.example.stocks.StocksViewModel
import com.example.stocks.assets.Bookmark
import com.example.stocks.assets.TrendingUpIcon
import com.example.stocks.ui.theme.sansFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    viewModel: StocksViewModel,
    ticker: String,
    price: String,
    changeAmount: String,
    changePercentage: String,
) {
    LaunchedEffect(true) {
        // viewModel.getCompanyOverviewData(ticker)
    }
    val navController = LocalNavController.current
    val ticker = "IBM"
    val companyName = "International Business Machines"
    val currentPrice = "291.16" // Calculated from market cap and shares outstanding
    val changeAmount = "+2.84"
    val changePercentage = "+0.99%"
    val isPositive = true
    val changeColor = if (isPositive) Color(0xFF00C853) else Color(0xFFD32F2F)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Overview", fontWeight = FontWeight.SemiBold
                    )
                }, navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack, contentDescription = "Back"
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Main Stock Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f)
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 1f),
                                    MaterialTheme.colorScheme.surface
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = ticker,
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                    letterSpacing = 1.2.sp
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = companyName,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                            }

                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                modifier = Modifier.size(48.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.size(48.dp)
                                ) {
                                    Icon(
                                        imageVector = Bookmark,
                                        contentDescription = "Bookmark",
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = "$$currentPrice",
                                style = MaterialTheme.typography.displaySmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = changeColor.copy(alpha = 0.1f),
                                modifier = Modifier.padding(bottom = 4.dp)
                            ) {
                                Row(
                                    modifier = Modifier.padding(
                                        horizontal = 12.dp, vertical = 6.dp
                                    ), verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = TrendingUpIcon,
                                        contentDescription = "Trend",
                                        tint = changeColor,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = changeAmount,
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = FontWeight.SemiBold,
                                        color = changeColor
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = changePercentage,
                            style = MaterialTheme.typography.bodyMedium,
                            color = changeColor,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Key Metrics Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard(
                    title = "Market Cap", value = "$270.5B", modifier = Modifier.weight(1f)
                )
                InfoCard(
                    title = "P/E Ratio", value = "49.75", modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard(
                    title = "EPS", value = "$5.85", modifier = Modifier.weight(1f)
                )
                InfoCard(
                    title = "Dividend Yield", value = "2.31%", modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                InfoCard(
                    title = "52W High", value = "$296.16", modifier = Modifier.weight(1f)
                )
                InfoCard(
                    title = "52W Low", value = "$165.52", modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // About Section
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column {
                    Text(
                        text = "About",
                        fontFamily = sansFontFamily,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "International Business Machines Corporation (IBM) is an American multinational technology company headquartered in Armonk, New York, with operations in over 170 countries. IBM produces and sells computer hardware, middleware and software, and provides hosting and consulting services in areas ranging from mainframe computers to nanotechnology.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                            modifier = Modifier.padding(10.dp),
                            lineHeight = 20.sp
                        )
                    }
                    Column(
                        modifier = Modifier.padding(top = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        HorizontalDivider(thickness = 2.dp)
                        CompanyDetailRow("Exchange", "NYSE")
                        HorizontalDivider(thickness = 2.dp)
                        CompanyDetailRow("Sector", "Technology")
                        HorizontalDivider(thickness = 2.dp)
                        CompanyDetailRow("Industry", "Computer & Office Equipment")
                        HorizontalDivider(thickness = 2.dp)
                        CompanyDetailRow("Headquarters", "Armonk, NY, USA")
                        HorizontalDivider(thickness = 2.dp)
                        CompanyDetailRow("Website", "www.ibm.com")
                        HorizontalDivider(thickness = 2.dp)
                        CompanyDetailRow("Employees", "185,719", isLast = true)

                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Additional Info Section


            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun InfoCard(
    title: String, value: String, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun CompanyDetailRow(
    label: String, value: String, isLast: Boolean = false
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label.uppercase(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = value,
                fontFamily = sansFontFamily,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        if (!isLast) {
            Spacer(modifier = Modifier.height(5.dp))
        }
    }
}