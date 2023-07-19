package com.example.lazycolumn.Presentation.Components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.FormValidation.RegisterFormEvent
import com.example.lazycolumn.R

@Composable
fun submitbutton(
    onClick: () -> Unit,
    loading:Boolean,
    buttontxt:String?
   ){
    Button(
        onClick = { onClick() },
        Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.teal_700)),
    ) {
        if (loading){
            Loadingbar()
        }
        else{
            tvcomponent(tvName = buttontxt)
        }


    }
}