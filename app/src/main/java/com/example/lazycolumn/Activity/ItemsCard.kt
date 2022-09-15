package com.example.lazycolumn.Activity

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.R

@Composable
fun ItemCard(name:String,id:Int){
    Card(

        modifier = Modifier
            .clickable {
                System.out.println("myid $id")
            }
            // The space between each card and the other
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = colorResource(id = R.color.white)

    ) {
        Row(Modifier.padding(8.dp)) {
            Image(
                painter = painterResource(id = R.drawable.homescreen),
                contentDescription = "Forest Image",
                modifier = Modifier.wrapContentHeight().wrapContentWidth().align(Alignment.CenterVertically),
                contentScale = ContentScale.Crop
            )

            Text(
                text = name,
                style = MaterialTheme.typography.h2,

                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                color = Color.Black,
            )





        }





    }
}