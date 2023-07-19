package com.example.lazycolumn.Presentation.Screens

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
import com.example.lazycolumn.ViewModel.LoginValidationViewModel
import kotlinx.coroutines.*
import androidx.compose.ui.platform.LocalConfiguration
import com.example.lazycolumn.Presentation.Components.Loadingbar
import com.example.lazycolumn.Presentation.Components.passwordfieldcomponent
import com.example.lazycolumn.Presentation.Components.submitbutton
import com.example.lazycolumn.Presentation.Components.textfieldcomponent
import com.example.lazycolumn.Presentation.Components.tvcomponent
import com.example.lazycolumn.ui.theme.Orange


@Composable
fun LoginPage(navController: NavController){
    val image= painterResource(id = R.drawable.login_image)
    val emailicon= painterResource(id = R.drawable.email_icon)

    val focusManager = LocalFocusManager.current
    val loading = remember { mutableStateOf(false) }
    val viewModel= viewModel<LoginValidationViewModel>()
    val state=viewModel.state
    val context= LocalContext.current

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp


    LaunchedEffect(key1 = context){
        viewModel.validationEvents.collect{event->
            when(event){
             is LoginValidationViewModel.ValidationEvent.success->{




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

        Image(painter = image,contentDescription = null, modifier = Modifier.height(screenHeight*0.15f))

        Text(text = "SIgn In", color = colorResource(id = R.color.black), fontSize = 21.sp)

        Spacer(modifier = Modifier.height(20.dp))



        Column( modifier = Modifier.fillMaxWidth(0.8f)) {

            textfieldcomponent(
                value = state.email,
                changevalue ={
                    viewModel.onEvent(LoginFormEvent.EmailChanged(it))
                } ,
                labeltext = "Email Address",
                placeholdertext = "Email Address",
                keyboardType = KeyboardType.Email ,
                icon = emailicon,
                isError = state.emailError !=null
            )


if (state.emailError!=null){
    Text(text = state.emailError, color = MaterialTheme.colors.error)
}
            Spacer(modifier = Modifier.height(20.dp))
            passwordfieldcomponent(
                value = state.password,
                changevalue = {
                    viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
                },
                isError = state.passwordError !=null
            )


            if (state.passwordError!=null){
                Text(text = state.passwordError!!, color = MaterialTheme.colors.error)
            }
                Spacer(modifier = Modifier.height(34.dp))
                submitbutton(
                onClick = {
                viewModel.onEvent(LoginFormEvent.Login)
                          },
                loading = loading.value,
                buttontxt = "Login"
                )

            Spacer(modifier = Modifier.height(25.dp))
            Text(text = "Register Account", color = colorResource(id = R.color.teal_700), fontSize = 19.sp, textAlign = TextAlign.Center, modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .clickable { navController.navigate(Screens.Register.route) })





        }








    }




}



  }



}
