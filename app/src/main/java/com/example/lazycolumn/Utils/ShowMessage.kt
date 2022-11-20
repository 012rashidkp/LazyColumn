package com.example.lazycolumn.Utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.R

@Preview
@Composable
fun ShowMessage(){
    Snackbar(
        modifier = Modifier
        .padding(4.dp)
        .background(colorResource(id = R.color.teal_700))) {
        Text(text = "This is a basic snackbar")
    }
}


