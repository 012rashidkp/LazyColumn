package com.example.lazycolumn.Navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun EnterExitAnimation(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            initialOffsetX = { -300 }, // small slide 300px
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing // interpolator
            )
        ),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}