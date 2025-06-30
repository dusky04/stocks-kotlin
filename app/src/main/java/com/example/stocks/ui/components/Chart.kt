import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.stocks.data.model.TimeSeriesEntry
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import androidx.compose.ui.graphics.Color as ComposeColor


@Composable
fun MPLineChart(
    modifier: Modifier = Modifier,
    data: Map<String, TimeSeriesEntry>,
    lineColor: ComposeColor
) {
    AndroidView(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth(),
        factory = { context ->
            LineChart(context)
        },
        update = { chart ->
            val sortedData = data.entries
                .mapNotNull { entry ->
                    val price = entry.value.close?.toDoubleOrNull()
                    if (price != null) entry.key to price else null
                }
                .sortedBy { it.first }

            if (sortedData.isEmpty()) {
                chart.clear()
                chart.invalidate()
                return@AndroidView
            }

            val prices = sortedData.map { it.second }
            val fullTimestamps = sortedData.map { it.first }

            val entries = prices.mapIndexed { index, price ->
                Entry(index.toFloat(), price.toFloat())
            }

            val lineDataSet = LineDataSet(entries, "Price").apply {
                color = lineColor.toArgb()
                valueTextColor = ComposeColor.Black.toArgb()
                setDrawValues(false) // Hide the values on the chart line
                lineWidth = 2f
                setDrawCircles(false) // Hide the dots on the data points
                setDrawFilled(true) // Enable the shadow/fill below the line
                fillColor = lineColor.toArgb()
                fillAlpha = 50 // Transparency of the fill
                mode = LineDataSet.Mode.CUBIC_BEZIER // Make the line curved
            }

            chart.xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(false) // Hide vertical grid lines
                setDrawAxisLine(false)
                textColor = ComposeColor.Gray.toArgb()
                granularity = 1f

                valueFormatter = object : ValueFormatter() {
                    private val labelsToDisplay = 4
                    private val step =
                        (fullTimestamps.size - 1) / (labelsToDisplay - 1).coerceAtLeast(1)
                    private val indicesToShow = (0 until labelsToDisplay).map { it * step }.toSet()

                    override fun getAxisLabel(
                        value: Float,
                        axis: AxisBase?
                    ): String {
                        val index = value.toInt()
                        return when {
                            index in indicesToShow || index == fullTimestamps.size - 1 -> {
                                fullTimestamps[index].substring(11, 16) // Format to HH:mm
                            }

                            else -> ""
                        }
                    }
                }
            }

            chart.axisRight.isEnabled = false // Disable the right Y-axis
            chart.axisLeft.apply {
                setDrawGridLines(true) // Show horizontal grid lines
                gridColor = ComposeColor.LightGray.copy(alpha = 0.5f).toArgb()
                setDrawAxisLine(false)
                textColor = ComposeColor.Gray.toArgb()
            }

            chart.description.isEnabled = false
            chart.legend.isEnabled = false
            chart.setTouchEnabled(true)
            chart.isDragEnabled = true
            chart.setScaleEnabled(true)
            chart.setPinchZoom(true)
            chart.setNoDataText("Loading chart data...")
            chart.setNoDataTextColor(ComposeColor.Gray.toArgb())
            chart.data = LineData(lineDataSet)
            chart.animateX(1000)
            chart.invalidate()
        }
    )
}