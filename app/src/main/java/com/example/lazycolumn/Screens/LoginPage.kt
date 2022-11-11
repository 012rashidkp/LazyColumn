package com.example.lazycolumn.Screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.R
import com.example.lazycolumn.FormValidation.LoginFormEvent
import com.example.lazycolumn.ViewModel.UserValidationViewModel
import kotlinx.coroutines.*

@Composable
fun LoginPage(navController: NavController){
    val image= painterResource(id = R.drawable.login_image)
    val emailicon= painterResource(id = R.drawable.email_icon)
    val passwordicon= painterResource(id = R.drawable.password_icon)
    val hidepasswordicon= painterResource(id = R.drawable.hide_password)
    val showpasswordicon= painterResource(id = R.drawable.show_password)
    val maincolor= colorResource(id = R.color.teal_700)
    val greycolor= colorResource(id = R.color.greycolor)

    val passwordvisibility= remember { mutableStateOf(false)}
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val loading = remember { mutableStateOf(false) }
    val viewModel= viewModel<UserValidationViewModel>()
    val state=viewModel.state
    val context= LocalContext.current




    LaunchedEffect(key1 = context){
        viewModel.validationEvents.collect{event->
            when(event){
             is UserValidationViewModel.ValidationEvent.success->{




               CoroutineScope(Dispatchers.IO).launch {
                   kotlinx.coroutines.delay(3000)
                   withContext(Dispatchers.Main) {
                       Log.i("TAG", "this will be called after 3 seconds")
                       navController.navigate(Screens.Home.route)
                       focusManager.clearFocus()
                   }
               }
                 loading.value=true

             }
         }
        }
    }

    
  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
Box(modifier = Modifier
    .fillMaxSize()
    .background(Color.White), contentAlignment = Alignment.TopCenter) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = image,contentDescription = null )

        Text(text = "SIgn In", color = colorResource(id = R.color.black), fontSize = 21.sp)

        Spacer(modifier = Modifier.height(20.dp))



        Column( modifier = Modifier.fillMaxWidth(0.8f)) {

                TextField(value = state.email, onValueChange ={ viewModel.onEvent(LoginFormEvent.EmailChanged(it)) },
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
                        textColor = colorResource(id = R.color.black)),
                    isError =state.emailError !=null ,
                    )
if (state.emailError!=null){
    Text(text = state.emailError, color = MaterialTheme.colors.error)
}


                Spacer(modifier = Modifier.height(20.dp))

                TextField(
                    value = state.password, onValueChange ={viewModel.onEvent(LoginFormEvent.PasswordChanged(it))},
                    label = { Text(text = "Password", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                    placeholder = { Text(text = "Password", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    leadingIcon = { Icon(painter = passwordicon, contentDescription = null, tint = colorResource(id = R.color.teal_700)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.greycolor)),
                    colors = TextFieldDefaults.textFieldColors(cursorColor = maincolor, focusedIndicatorColor = greycolor, textColor = colorResource(id = R.color.black)),
                    isError =state.passwordError !=null,
                    trailingIcon = { Icon(painter = if (!passwordvisibility.value) hidepasswordicon else showpasswordicon , contentDescription =null,tint=maincolor,modifier = Modifier
                        .size(28.dp)
                        .clickable { passwordvisibility.value = !passwordvisibility.value })},
                    visualTransformation = if (passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation()
                )
            if (state.passwordError!=null){
                Text(text = state.passwordError!!, color = MaterialTheme.colors.error)
            }
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
                            viewModel.onEvent(LoginFormEvent.Login)
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
                .clickable { navController.navigate(Screens.Register.route) })





        }








    }




}



  }



}
