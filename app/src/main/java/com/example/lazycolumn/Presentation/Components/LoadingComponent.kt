package com.example.lazycolumn.Presentation.Components

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Loadingbar(){
    CircularProgressIndicator(
        modifier = Modifier
        .wrapContentHeight()
        .wrapContentWidth(),
        color = Color.White

    )
}