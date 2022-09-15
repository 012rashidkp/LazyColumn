package com.example.lazycolumn.Screens

import android.graphics.Paint
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.internal.enableLiveLiterals
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lazycolumn.Activity.MainActivity
import com.example.lazycolumn.ApiCalls.Loginuser
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.R


@Composable
fun LoginPage(navController: NavController){
    val image= painterResource(id = R.drawable.login_image)
    val emailicon= painterResource(id = R.drawable.email_icon)
    val passwordicon= painterResource(id = R.drawable.password_icon)
    val hidepasswordicon= painterResource(id = R.drawable.hide_password)
    val showpasswordicon= painterResource(id = R.drawable.show_password)
    val maincolor= colorResource(id = R.color.teal_700)
    val greycolor= colorResource(id = R.color.greycolor)
    val emailvalue= remember { mutableStateOf("")}
    val passwordvalue= remember { mutableStateOf("")}

    var email by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }





    val passwordvisibility= remember { mutableStateOf(false)}
    val focusRequester= remember { mutableStateOf(FocusRequester)}
    val loading = remember { mutableStateOf(false) }
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
Box(modifier = Modifier
    .fillMaxSize()
    .background(Color.White), contentAlignment = Alignment.TopCenter) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = image,contentDescription = null )

        Text(text = "SIgn In", color = colorResource(id = R.color.black), fontSize = 21.sp)

        Spacer(modifier = Modifier.height(20.dp))
        Column( modifier = Modifier
            .fillMaxWidth(0.8f)) {

                TextField(

                    value = email, onValueChange ={email=it},
                    label = { Text(text = "Email Address", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                    placeholder = { Text(text = "Email Address", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                    singleLine = true,

                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = { Icon(painter = emailicon, contentDescription = null, tint = colorResource(id = R.color.teal_700)) },

                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.greycolor)),
                    colors = TextFieldDefaults.textFieldColors(

                        cursorColor = maincolor,
                        focusedIndicatorColor = greycolor,
                        focusedLabelColor = maincolor,
                        textColor = colorResource(id = R.color.black)


                    ),
                    shape = RoundedCornerShape(12.dp),



                    )

                Spacer(modifier = Modifier.height(20.dp))

                TextField(

                    value = password, onValueChange ={password=it},
                    label = { Text(text = "Password", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                    placeholder = { Text(text = "Password", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = { Icon(painter = passwordicon, contentDescription = null, tint = colorResource(id = R.color.teal_700)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.greycolor)),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = maincolor,
                        focusedIndicatorColor = greycolor,
                        textColor = colorResource(id = R.color.black)
                    ),
                    trailingIcon = { Icon(painter = if (!passwordvisibility.value) hidepasswordicon else showpasswordicon , contentDescription =null,tint=maincolor ,   modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            passwordvisibility.value = !passwordvisibility.value
                        })},

                    visualTransformation = if (passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(34.dp))
                Box(

                    Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(
                            colorResource(id = R.color.teal_700),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            loading.value = !loading.value
                            //   Loginuser(activity = MainActivity(), email, password, navController)
                        }) {
                    if (loading.value){
                        CircularProgressIndicator(modifier = Modifier
                            .wrapContentHeight()
                            .wrapContentWidth()
                            .align(alignment = Alignment.Center),color= colorResource(
                            id = R.color.white
                        ))
                    }
                    else{
                        Text(text = "Login", color = colorResource(id = R.color.white), fontSize = 19.sp, textAlign = TextAlign.Center, modifier=Modifier.align(alignment = Alignment.Center))

                    }


                }





            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Register Account", color = colorResource(id = R.color.teal_700), fontSize = 19.sp, textAlign = TextAlign.Center, modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate(Screens.Register.route)

                })





        }








    }




}



  }



}