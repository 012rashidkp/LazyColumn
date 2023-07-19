package com.example.lazycolumn.Presentation.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.lazycolumn.FormValidation.RegisterFormEvent
import com.example.lazycolumn.R

@Composable
fun textfieldcomponent(
    value:String?,
    changevalue: (String) -> Unit,
    labeltext:String?=null,
    placeholdertext:String?,
    keyboardType: KeyboardType,
    icon:Painter?=null,
    isError:Boolean

){
    val maincolor= colorResource(id = R.color.teal_700)
    val greycolor= colorResource(id = R.color.greycolor)
    val defaulticon= painterResource(id = R.drawable.default_icon)
    val tealcolor= colorResource(id = R.color.teal_700)
    val white= colorResource(id = R.color.white)
    var mycolor:Any?=null
    if (icon!=null) mycolor=tealcolor else mycolor=greycolor

   TextField(
       value = value!!,
       onValueChange = changevalue,
       label = { Text(text = labeltext?:"",
       color = colorResource(id = R.color.black),
           textAlign = TextAlign.Center
           )},
       placeholder = {
           Text(text = placeholdertext?:"",
           color = colorResource(id = R.color.black),
           textAlign = TextAlign.Center
               )
               },
       keyboardOptions = KeyboardOptions(keyboardType = keyboardType),

       singleLine = true,
       leadingIcon = { Icon(painter = icon?:defaulticon, contentDescription = null, tint = mycolor) },
       modifier = Modifier
           .fillMaxWidth()
           .background(greycolor),
       shape = RoundedCornerShape(12.dp),
       colors = TextFieldDefaults.textFieldColors(
           cursorColor = maincolor,
           focusedIndicatorColor = greycolor,
           textColor = colorResource(id = R.color.black)
       ),
       isError = isError,
       )
}
@Composable
fun passwordfieldcomponent(
    value:String?,
    changevalue: (String) -> Unit,
    isError:Boolean
){
    val passwordvisibility= remember { mutableStateOf(false) }
    val passwordicon= painterResource(id = R.drawable.password_icon)
    val hidepasswordicon= painterResource(id = R.drawable.hide_password)
    val showpasswordicon= painterResource(id = R.drawable.show_password)
    val maincolor= colorResource(id = R.color.teal_700)
    val greycolor= colorResource(id = R.color.greycolor)

    TextField(

        value = value!!, onValueChange =changevalue,
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

        isError = isError,
        visualTransformation = if (passwordvisibility.value) VisualTransformation.None else PasswordVisualTransformation()
    )
}