package com.example.lazycolumn.Screens


import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import com.example.lazycolumn.R
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.navigation.NavHostController
import com.example.lazycolumn.Model.DataOrException
import com.example.lazycolumn.Model.Products
import com.example.lazycolumn.Model.listitems
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.ViewModel.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlin.math.ceil


@Composable
fun HomeScreen(navController: NavHostController) {
    val context= LocalContext.current
    val loading = remember { mutableStateOf(true) }

LaunchedEffect(key1 =context){
    CoroutineScope(Dispatchers.IO).launch {
        delay(3000)
        withContext(Dispatchers.Main){
            loading.value=false
        }
    }
}


    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {

        if (loading.value){
            CircularProgressIndicator(modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(alignment = Alignment.Center),color= colorResource(
                id = R.color.teal_700
            ))
        }
        else{
            showgridmenus(navController)
        }







    }


}
@Composable
fun showgridmenus(navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),


        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(listitems.size) { index ->
                Card(
                    backgroundColor = colorResource(id = R.color.teal_700),
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            when (listitems[index].itemid) {
                                1 -> navController.navigate(Screens.ListPage.route)
                                7 -> navController.navigate(Screens.UploadScreen.route)
                            }
                        },
                    elevation = 8.dp,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(10.dp))
                        Image(painter = painterResource(id = listitems[index].itemIcon), contentDescription = null,
                            colorFilter = ColorFilter.tint(color = colorResource(id = R.color.white))
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = listitems[index].itemName, style = TextStyle(color = colorResource(id = R.color.white)))

                    }
                }
            }
        }
    )
}


















