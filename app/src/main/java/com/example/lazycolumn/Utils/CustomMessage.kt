package com.example.lazycolumn.Utils


import androidx.compose.ui.geometry.Offset.Companion.Unspecified
import androidx.compose.ui.geometry.Size.Companion.Unspecified
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.AlignmentLine.Companion.Unspecified
import com.radusalagean.infobarcompose.BaseInfoBarMessage

class CustomMessage(
    val textString: String,
    val icon: ImageVector,
    val iconColor: Color,
    val textColor: Color = Color.Unspecified,
    override val backgroundColor: Color? = null,
    override val displayTimeSeconds: Int? = 4,

    ):BaseInfoBarMessage() {
    override val containsControls: Boolean = false

    }