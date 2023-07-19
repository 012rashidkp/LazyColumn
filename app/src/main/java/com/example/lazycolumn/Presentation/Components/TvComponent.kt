package com.example.lazycolumn.Presentation.Components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.lazycolumn.R

@Composable
fun tvcomponent(tvName:String?){
    Text(text = "${tvName}", color = colorResource(id = R.color.white), fontSize = 19.sp, textAlign = TextAlign.Center)

}