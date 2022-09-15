package com.example.lazycolumn.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.lazycolumn.R
import com.example.lazycolumn.ui.theme.PlantsInCosmeticsTheme


@Composable
fun HomeScreen(navController: NavHostController){
    PlantsInCosmeticsTheme() {

        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally


            ) {
                val username = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }


                Text(text = "Login", style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.Cursive))

                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it })
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    label = { Text(text = "Password") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })
                Spacer(modifier = Modifier.height(20.dp))


                Box(modifier = Modifier
                    .clickable { }
                    .height(60.dp)
                    .fillMaxWidth()
                    .border(width = 1.dp, colorResource(id = R.color.teal_700), shape = RoundedCornerShape(10.dp))

                    .background(
                        colorResource(id = R.color.teal_700)

                    ),


                    ){
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier
                            .wrapContentWidth()
                            .wrapContentHeight()
                            .align(Alignment.Center)

                        ,

                        )
                }



            }







        }
    }



}