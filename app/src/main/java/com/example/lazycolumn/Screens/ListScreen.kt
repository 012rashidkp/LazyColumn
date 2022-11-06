package com.example.lazycolumn.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoroutinesApi
@Composable
fun ListScreen(navController: NavHostController){
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        backgroundColor = colorResource(id = R.color.white),


        floatingActionButton = {
            FloatingActionButton(
                onClick = {
             navController.navigate(Screens.CreatePage.route)
                },
                backgroundColor = colorResource(id = R.color.teal_700),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        tint = colorResource(id = R.color.white)
                    )
                }
            )
        },

        content = { Text("BodyContent", style = TextStyle(fontSize = 18.sp, fontFamily = FontFamily.SansSerif)) },

        )





}