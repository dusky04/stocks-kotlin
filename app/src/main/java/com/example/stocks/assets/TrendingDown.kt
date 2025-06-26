package com.example.stocks.assets


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TrendingDownIcon: ImageVector
    get() {
        if (_TrendingDownIcon != null) return _TrendingDownIcon!!

        _TrendingDownIcon = ImageVector.Builder(
            name = "Trending_down",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(640f, 720f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(104f)
                lineTo(536f, 434f)
                lineTo(376f, 594f)
                lineTo(80f, 296f)
                lineToRelative(56f, -56f)
                lineToRelative(240f, 240f)
                lineToRelative(160f, -160f)
                lineToRelative(264f, 264f)
                verticalLineToRelative(-104f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(240f)
                close()
            }
        }.build()

        return _TrendingDownIcon!!
    }

private var _TrendingDownIcon: ImageVector? = null

