package com.example.lazycolumn.Common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val content: @Composable (CustomMessage) -> Unit = { message ->
    Row {
        Icon(
            modifier = Modifier.padding(8.dp).align(Alignment.CenterVertically),
            imageVector = message.icon,
            contentDescription = null,
            tint = message.iconColor
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = message.textString,
            color = message.textColor
        )
    }
}
