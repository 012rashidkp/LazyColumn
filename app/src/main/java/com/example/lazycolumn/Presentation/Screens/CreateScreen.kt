package com.example.lazycolumn.Presentation.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lazycolumn.Activity.MainActivity
import com.example.lazycolumn.FormValidation.ProductFormEvent
import com.example.lazycolumn.R
import com.example.lazycolumn.ViewModel.ProductDataViewModel
import com.example.lazycolumn.ViewModel.ProductValidationViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun CreateScreen(navController: NavHostController){

    val productDataViewModel:ProductDataViewModel= hiltViewModel()

    val productsavingviewmodel= viewModel<ProductValidationViewModel>()
    val state=productsavingviewmodel.state
    val context= LocalContext.current
    val loading = remember { mutableStateOf(false) }

    var prodName by remember { mutableStateOf("prodName") }
    var proddesc by remember { mutableStateOf("proddesc") }
    var prodprice by remember { mutableStateOf("prodprice") }
    var prodqty by remember { mutableStateOf("prodqty") }

    LaunchedEffect(key1 = context) {
        productsavingviewmodel.validationEvents.collect { event ->
            when (event) {
                is ProductValidationViewModel.ValidationEvent.success -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        kotlinx.coroutines.delay(3000)
                        withContext(Dispatchers.Main) {
                            (context as MainActivity).onBackPressed()
                        } }
                    loading.value = true
                    prodName=productsavingviewmodel.state.productName
                    proddesc=productsavingviewmodel.state.productdesc
                    prodprice=productsavingviewmodel.state.productprice
                    prodqty=productsavingviewmodel.state.productqty
                    productDataViewModel.insertproducts(prodName,proddesc,prodprice.toDouble(),prodqty.toLong())


                }
            }
        }
    }






    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
   Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(0.8f)) {

       Spacer(modifier = Modifier.height(20.dp))

       TextField(
           value =state.productName, onValueChange = {productsavingviewmodel.onEvent(ProductFormEvent.ProductNameChanged(it)) },
           label = {
               Text(
                   text = "Product Name",
                   color = colorResource(id = R.color.black),
                   textAlign = TextAlign.Center
               )
           },
           placeholder = {
               Text(
                   text = "Product Name",
                   color = colorResource(id = R.color.black),
                   textAlign = TextAlign.Center
               )
           },
           singleLine = true,
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
           modifier = Modifier
               .background(colorResource(id = R.color.greycolor))
               .fillMaxWidth(),
           colors = TextFieldDefaults.textFieldColors(
               cursorColor = colorResource(id = R.color.black),
               focusedIndicatorColor = colorResource(id = R.color.greycolor),
               focusedLabelColor = colorResource(id = R.color.black),
               textColor = colorResource(id = R.color.black),),
               isError = state.productNameError!=null,
           )

       if (state.productNameError!=null){
           Text(text = state.productNameError, color = MaterialTheme.colors.error)
       }
       Spacer(modifier = Modifier.height(20.dp))
       TextField(value = state.productdesc, onValueChange ={productsavingviewmodel.onEvent(ProductFormEvent.productdescChanged(it))},
           label = { Text(text = "Product Desc", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
           placeholder = { Text(text = "Product Desc", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
           singleLine = true,
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
           modifier = Modifier
               .background(colorResource(id = R.color.greycolor))
               .fillMaxWidth(),
           colors = TextFieldDefaults.textFieldColors(
               cursorColor = colorResource(id = R.color.black),
               focusedIndicatorColor = colorResource(id = R.color.greycolor),
               focusedLabelColor = colorResource(id = R.color.black),
               textColor = colorResource(id = R.color.black)
           ),
            isError = state.productdescError!=null

       )
       if (state.productdescError!=null){
           Text(text = state.productdescError, color = MaterialTheme.colors.error)
       }

       Spacer(modifier = Modifier.height(20.dp))
       TextField(value = state.productprice, onValueChange ={productsavingviewmodel.onEvent(ProductFormEvent.productpricechanged(it))},
           label = { Text(text = "Product Price", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
           placeholder = { Text(text = "Product Price", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
           singleLine = true,
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
           modifier = Modifier
               .background(colorResource(id = R.color.greycolor))
               .fillMaxWidth(),
           colors = TextFieldDefaults.textFieldColors(
               cursorColor = colorResource(id = R.color.black),
               focusedIndicatorColor = colorResource(id = R.color.greycolor),
               focusedLabelColor = colorResource(id = R.color.black),
               textColor = colorResource(id = R.color.black)
           ),
            isError =state.productpriceError!=null

       )
       if (state.productpriceError!=null){
           Text(text = state.productpriceError, color = MaterialTheme.colors.error)
       }

       Spacer(modifier = Modifier.height(20.dp))
       TextField(value = state.productqty, onValueChange ={productsavingviewmodel.onEvent(ProductFormEvent.productqtychanged(it))},
           label = { Text(text = "Product Qty", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
           placeholder = { Text(text = "Product Qty", color = colorResource(id = R.color.black), textAlign = TextAlign.Center) },
           singleLine = true,
           keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
           modifier = Modifier
               .background(colorResource(id = R.color.greycolor))
               .fillMaxWidth(),
           colors = TextFieldDefaults.textFieldColors(
               cursorColor = colorResource(id = R.color.black),
               focusedIndicatorColor = colorResource(id = R.color.greycolor),
               focusedLabelColor = colorResource(id = R.color.black),
               textColor = colorResource(id = R.color.black)
           ),
           isError = state.productqtyError!=null
       )
       if (state.productqtyError!=null){
           Text(text = state.productqtyError, color = MaterialTheme.colors.error)
       }

       Spacer(modifier = Modifier.height(35.dp))

       Box(
           Modifier
               .fillMaxWidth()
               .height(60.dp)
               .background(
                   colorResource(id = R.color.teal_700),
                   shape = RoundedCornerShape(12.dp)
               )
               .clickable {

                   productsavingviewmodel.onEvent(ProductFormEvent.Saveproducts)
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
               Text(text = "Save Data", color = colorResource(id = R.color.white), fontSize = 19.sp, textAlign = TextAlign.Center, modifier=Modifier.align(alignment = Alignment.Center))

           }


       }


   }





    }



}