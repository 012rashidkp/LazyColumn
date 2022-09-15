package com.example.lazycolumn.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.R

@Composable
fun RegisterPage(navController: NavController){
    val image= painterResource(id = R.drawable.login_image)
    val emailicon= painterResource(id = R.drawable.email_icon)
    val usernameicon= painterResource(id = R.drawable.username_icon)
    val phoneicon= painterResource(id = R.drawable.phone_icon)
    val cityicon= painterResource(id = R.drawable.city_icon)
    val passwordicon= painterResource(id = R.drawable.password_icon)
    val hidepasswordicon= painterResource(id = R.drawable.hide_password)
    val showpasswordicon= painterResource(id = R.drawable.show_password)
    val maincolor= colorResource(id = R.color.teal_700)
    val greycolor= colorResource(id = R.color.greycolor)
    val emailvalue= remember { mutableStateOf("") }
    val usernamevalue= remember { mutableStateOf("")}
    val phonenumbervalue= remember { mutableStateOf("")}
    val cityvalue= remember { mutableStateOf("")}
    val passwordvalue= remember { mutableStateOf("") }
    val passwordvisibility= remember { mutableStateOf(false) }
    val focusRequester= remember { mutableStateOf(FocusRequester) }
    val loading = remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.TopCenter) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = image,contentDescription = null )

                Text(text = "SIgnup Here", color = colorResource(id = R.color.black), fontSize = 21.sp)

                Spacer(modifier = Modifier.height(20.dp))
                Column( modifier = Modifier
                    .fillMaxWidth(0.8f)) {

                    TextField(

                        value = emailvalue.value, onValueChange ={emailvalue.value=it},
                        label = { Text(text = "Email Address", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        placeholder = { Text(text = "Email Address", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        leadingIcon = { Icon(painter = emailicon, contentDescription = null, tint = colorResource(id = R.color.teal_700)) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.greycolor)),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = maincolor,
                            focusedIndicatorColor = greycolor,
                            textColor = colorResource(id = R.color.black)
                        ),


                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(

                        value = usernamevalue.value, onValueChange ={usernamevalue.value=it},
                        label = { Text(text = "User Name", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        placeholder = { Text(text = "User Name", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        leadingIcon = { Icon(painter = usernameicon, contentDescription = null, tint = colorResource(id = R.color.teal_700)) },

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.greycolor)),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = maincolor,
                            focusedIndicatorColor = greycolor,
                            textColor = colorResource(id = R.color.black)
                        ),

                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(

                        value = phonenumbervalue.value, onValueChange ={phonenumbervalue.value=it},
                        label = { Text(text = "Phone Number", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        placeholder = { Text(text = "Phone Number", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),

                        leadingIcon = { Icon(painter = phoneicon, contentDescription = null, tint = colorResource(id = R.color.teal_700)) },

                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.greycolor)),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = maincolor,
                            focusedIndicatorColor = greycolor,
                            textColor = colorResource(id = R.color.black)
                        ),

                    )


                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(

                        value = cityvalue.value, onValueChange ={cityvalue.value=it},
                        label = { Text(text = "City Name", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        placeholder = { Text(text = "Phone Number", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        singleLine = true,
                        leadingIcon = { Icon(painter = cityicon, contentDescription = null, tint = colorResource(id = R.color.teal_700)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colorResource(id = R.color.greycolor)),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = maincolor,
                            focusedIndicatorColor = greycolor,
                            textColor = colorResource(id = R.color.black)
                        ),

                    )



                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(

                        value = passwordvalue.value, onValueChange ={passwordvalue.value=it},
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
                        trailingIcon = { Icon(painter = if (!passwordvisibility.value) hidepasswordicon else showpasswordicon , contentDescription =null,tint=maincolor ,   modifier = Modifier.size(28.dp).clickable {
                            passwordvisibility.value=!passwordvisibility.value
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
                            ).clickable {
                                loading.value = !loading.value
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
                            Text(text = "Register", color = colorResource(id = R.color.white), fontSize = 19.sp, textAlign = TextAlign.Center, modifier= Modifier.align(alignment = Alignment.Center))

                        }

                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    Text(text = "Already a Account", color = colorResource(id = R.color.teal_700), fontSize = 19.sp, textAlign = TextAlign.Center, modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .clickable {
                            navController.navigate(Screens.Login.route)

                        })


                }




            }




        }



    }





}