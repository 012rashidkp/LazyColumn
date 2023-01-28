package com.example.lazycolumn.Common


import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.radusalagean.infobarcompose.BaseInfoBarMessage

class CustomMessage(
    val textString: String,
    val icon: ImageVector,
    val iconColor: Color=Color.White,
    val textColor: Color = Color.White,
    override val backgroundColor: Color? = null,
    override val displayTimeSeconds: Int? = 4,

    ):BaseInfoBarMessage() {
    override val containsControls: Boolean = false

    }