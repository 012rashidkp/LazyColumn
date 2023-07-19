package com.example.lazycolumn.Presentation.Components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lazycolumn.R

@Composable
fun imagecontent(uri: Uri?){
    val pickimage= painterResource(id = R.drawable.pick_image)
    if (uri!=null){
        AsyncImage(
            model = uri,
            contentDescription = "",
            modifier = Modifier
                .width(65.dp)
                .height(65.dp),
            contentScale = ContentScale.Crop
        )
    }
    else{
        Image(painter = pickimage, contentDescription =null,
        modifier = Modifier.height(65.dp)
            .width(65.dp)

            )
    }
}