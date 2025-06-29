package com.composables

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val NewsIcon: ImageVector
    get() {
        if (_NewsIcon != null) return _NewsIcon!!

        _NewsIcon = ImageVector.Builder(
            name = "Newsstand",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000))
            ) {
                moveTo(80f, 800f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(800f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(80f, -160f)
                verticalLineToRelative(-320f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(320f)
                close()
                moveToRelative(160f, 0f)
                verticalLineToRelative(-480f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(480f)
                close()
                moveToRelative(160f, 0f)
                verticalLineToRelative(-480f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(480f)
                close()
                moveToRelative(280f, 0f)
                lineTo(600f, 360f)
                lineToRelative(70f, -40f)
                lineToRelative(160f, 280f)
                close()
            }
        }.build()

        return _NewsIcon!!
    }

private var _NewsIcon: ImageVector? = null

