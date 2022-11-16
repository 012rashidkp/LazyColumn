package com.example.lazycolumn.Screens

import android.graphics.Color
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import com.example.lazycolumn.R
import coil.compose.AsyncImage

@Composable
fun ImagePreviewItem(
    uri: Uri,
    height: Dp,
    width: Dp,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AsyncImage(
            model = uri,
            contentDescription = "",
            modifier = Modifier
                .width(width)
                .height(height),
            contentScale = ContentScale.Crop
        )

        IconButton(onClick = { onClick() }) {
            Icon(imageVector = Icons.Default.Delete,
                contentDescription = "",
                modifier = Modifier
                    .size(45.dp),
                tint = colorResource(id = R.color.red)
            )
        }
    }
}