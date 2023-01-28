package com.example.lazycolumn.Common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.stevdzasan.messagebar.ContentWithMessageBar
import com.stevdzasan.messagebar.rememberMessageBarState

@Composable
fun Messagebar(){
    val state = rememberMessageBarState()
    ContentWithMessageBar(messageBarState = state) {

    }
}