package com.example.stocks.assets

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val TrendingUpIcon: ImageVector
    get() {
        if (_TrendingUpIcon != null) return _TrendingUpIcon!!

        _TrendingUpIcon = ImageVector.Builder(
            name = "Trending_up",
            defaultWidth = 24.dp,   // Match your modifier.size(40.dp)
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(136f, 720f)
                lineTo(80f, 664f)
                lineTo(376f, 366f)
                lineTo(536f, 526f)
                lineTo(744f, 320f)
                lineTo(640f, 320f)
                lineTo(640f, 240f)
                lineTo(880f, 240f)
                lineTo(880f, 480f)
                lineTo(800f, 480f)
                lineTo(800f, 376f)
                lineTo(536f, 640f)
                lineTo(376f, 480f)
                close()
            }
        }.build()

        return _TrendingUpIcon!!
    }

private var _TrendingUpIcon: ImageVector? = null

