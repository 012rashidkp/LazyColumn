package com.example.lazycolumn.Presentation.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lazycolumn.FormValidation.RegisterFormEvent
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.R
import com.example.lazycolumn.Common.CustomMessage
import com.example.lazycolumn.Common.DataStore
import com.example.lazycolumn.Common.content
import com.example.lazycolumn.ViewModel.AuthViewModel
import com.example.lazycolumn.ViewModel.RegisterValidationViewModel
import com.radusalagean.infobarcompose.InfoBar
import kotlinx.coroutines.*


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
    val passwordvisibility= remember { mutableStateOf(false) }
    val focusRequester= remember { mutableStateOf(FocusRequester) }
    val loading = remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val registerviewModel= viewModel<RegisterValidationViewModel>()
    val state=registerviewModel.state
    val context= LocalContext.current
    val authviewmodel= hiltViewModel<AuthViewModel>()
    val authstate=authviewmodel.state.value
    val responsestate= remember { mutableStateOf(0) }
    val showmessage= remember { mutableStateOf("") }
    var message: CustomMessage? by remember { mutableStateOf(null) }

    val scope = rememberCoroutineScope()
    // datastore Email
    val dataStore = DataStore(context)
    // get saved email
//    val savedEmail = dataStore.getEmail.collectAsState(initial = "")
if (authstate.error==true){
    System.out.println("error_state ${authstate.error}")
    responsestate.value=2
    showmessage.value=authstate.register_response!!.message
}
else if (authstate.error==false){
    System.out.println("error_state ${authstate.error}")
    responsestate.value=1
    showmessage.value=authstate.register_response!!.message

}
else if (authstate.loading){
    System.out.println("error_state ${authstate.error}")
    responsestate.value=0
}
else{
    System.out.println("error_state failure")
    responsestate.value=3
    showmessage.value="something went wrong"
    authstate.failure?.let { it }
}










LaunchedEffect(key1 = context){
    registerviewModel.validationEvents.collect { event ->
        when(event){
            is RegisterValidationViewModel.ValidationEvent.success->{
                loading.value=true
                authviewmodel.Registeruser(registerviewModel.state.username,registerviewModel.state.email,registerviewModel.state.phone,registerviewModel.state.city,registerviewModel.state.password)


                CoroutineScope(Dispatchers.IO).launch {
                    delay(3000)
                    withContext(Dispatchers.Main) {
                         if (responsestate.value==1){
                             message= CustomMessage(
                                 textString = showmessage.value,
                                 icon = Icons.Rounded.Done,
                                 backgroundColor =Color(0xFF018786)
                             )

                             scope.launch {
                                 dataStore.saveEmail(authstate.register_response!!.email)
                                 dataStore.savePhone(authstate.register_response!!.phone)
                                 dataStore.saveusername(authstate.register_response!!.username)
                                 dataStore.saveusercity(authstate.register_response!!.city)
                                 dataStore.saveusertoken(authstate.register_response.token)

                             }


                             navController.popBackStack()

                             System.out.println("authstate_1_value ${responsestate.value}")
                         }
                        else if (responsestate.value==2){
                             message= CustomMessage(
                                 textString = showmessage.value,
                                 icon = Icons.Rounded.Done,
                                 backgroundColor =Color(0xFF018786)
                             )
                            loading.value=false
                         }
                        else if (responsestate.value==3){
                             message= CustomMessage(
                                 textString = showmessage.value,
                                 icon = Icons.Rounded.Done,
                                 backgroundColor =Color(0xFF018786)
                             )
                             loading.value=false


                         }

                    }

                }




            }
        }
    }

}







    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.TopCenter) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Image(painter = image,contentDescription = null, modifier = Modifier.height(screenHeight*0.1f ) )

                Text(text = "SIgnup Here", color = colorResource(id = R.color.black), fontSize = 21.sp)

                Spacer(modifier = Modifier.height(10.dp))
                Column( modifier = Modifier
                    .fillMaxWidth(0.8f)) {

                    TextField(

                        value = state.email, onValueChange ={registerviewModel.onEvent(RegisterFormEvent.EmailChanged(it))},
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
                        isError = state.emailError !=null,

                    )
                    if (state.emailError!=null){
                        Text(text = state.emailError, color = MaterialTheme.colors.error)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    TextField(

                        value = state.username, onValueChange ={registerviewModel.onEvent(RegisterFormEvent.UserNameChanged(it))},
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
                   isError = state.usernameError!=null
                    )
                    if (state.usernameError!=null){
                        Text(text = state.usernameError, color = MaterialTheme.colors.error)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    TextField(

                        value = state.phone, onValueChange ={registerviewModel.onEvent(RegisterFormEvent.PhoneChanged(it))},
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
                     isError = state.phoneError!=null
                    )
                    if (state.phoneError!=null){
                        Text(text = state.phoneError, color = MaterialTheme.colors.error)
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    TextField(

                        value = state.city, onValueChange ={registerviewModel.onEvent(RegisterFormEvent.CityChanged(it))},
                        label = { Text(text = "City Name", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                        placeholder = { Text(text = "City Name", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
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
                     isError =state.cityError!=null
                    )
                    if (state.cityError!=null){
                        Text(text = state.cityError, color = MaterialTheme.colors.error)
                    }


                    Spacer(modifier = Modifier.height(15.dp))

                    TextField(

                        value = state.password, onValueChange ={registerviewModel.onEvent(RegisterFormEvent.PasswordChanged(it))},
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

                       isError = state.passwordError!=null,
                        visualTransformation = if (passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation()
                    )
                    if (state.passwordError!=null){
                        Text(text = state.passwordError, color = MaterialTheme.colors.error)
                    }



                    Spacer(modifier = Modifier.height(20.dp))






                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(
                                colorResource(id = R.color.teal_700),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .clickable {
                                registerviewModel.onEvent(RegisterFormEvent.Register)
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
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Already a Account", color = colorResource(id = R.color.teal_700), fontSize = 19.sp, textAlign = TextAlign.Center, modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally)
                        .clickable {
                            navController.navigate(Screens.Login.route)

                        })




                }




            }




        }

        InfoBar(offeredMessage = message, content = content) {
            message = null
        }

    }





}
