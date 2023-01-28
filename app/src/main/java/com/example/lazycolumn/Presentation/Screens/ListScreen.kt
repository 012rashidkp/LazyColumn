package com.example.lazycolumn.Presentation.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*



import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lazycolumn.FormValidation.ProductFormEvent
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.R
import com.example.lazycolumn.ViewModel.ProductDataViewModel
import com.example.lazycolumn.ViewModel.ProductValidationViewModel
import demo.productdb.ProductEntity
import kotlinx.coroutines.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoroutinesApi
@Composable
fun ListScreen(navController: NavHostController){
  val productDataViewModel:ProductDataViewModel= hiltViewModel()
val products=productDataViewModel.products.collectAsState(initial = emptyList()).value
    val context= LocalContext.current
    val loading = remember { mutableStateOf(true) }

    LaunchedEffect(key1 =context){
        CoroutineScope(Dispatchers.IO).launch {
            delay(2500)
            withContext(Dispatchers.Main){
                loading.value=false
            }
        }
    }




    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white)),
        backgroundColor = colorResource(id = R.color.greycolor),


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

        content = {
            if (loading.value){
             ShowLoading()
            }
            else{
                Showproductslist(products,productDataViewModel)
            }




                  },

        )

}
@Composable
fun ShowLoading(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator(modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth()
            .align(alignment = Alignment.Center),color= colorResource(
            id = R.color.teal_700
        ))
    }
}



@Composable
fun Showproductslist(products: List<ProductEntity>, productDataViewModel: ProductDataViewModel) {
    Box(contentAlignment = Alignment.TopCenter, modifier = Modifier
        .fillMaxSize()
        .padding(top = 15.dp)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            items(items =products,){ items->
                loadingcards(items,productDataViewModel)
            }
        }
    }
}
@Composable
fun Showalertmesage(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
       Text(text = "No Items Found",color=Color.Black, style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold ), modifier = Modifier
           .wrapContentHeight()
           .wrapContentWidth()
           .align(
               Alignment.Center
           ))
    }
}






@Composable
fun loadingcards(items: ProductEntity,productDataViewModel: ProductDataViewModel) {
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value) {
        alert(msg = "Product Details",
            showDialog = showDialog.value,
            productDataViewModel,
            onDismiss = {showDialog.value = false})
    }


    Card(backgroundColor = colorResource(id = R.color.white),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                showDialog.value = true
                productDataViewModel.getproductbyid(items.id)

            }
            .padding(horizontal = 10.dp)
            .wrapContentHeight(), elevation = 8.dp,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.90f)) {
                Text(
                    text = items.productName,
                    color = Color.Black,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text =items.prodDesc,
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }
            IconButton(onClick = {productDataViewModel.deleteproduct(items.id)}) {
                Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription =null,
                    tint = colorResource(id = R.color.teal_700)
                )

            }


        }



    }
}

@Composable
fun alert(msg: String,showDialog: Boolean,productDataViewModel: ProductDataViewModel,   onDismiss: () -> Unit) {
    val productsavingviewmodel= viewModel<ProductValidationViewModel>()
    val state=productsavingviewmodel.state
    val context= LocalContext.current

    if (showDialog) {
        val loading = remember { mutableStateOf(false) }
        productDataViewModel.productsdetails.let { products->

            AlertDialog(
                backgroundColor = colorResource(id = R.color.white),
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

                        Spacer(modifier = Modifier.height(20.dp))

                        TextField(
                            value ="${products?.productName}", onValueChange = {productsavingviewmodel.onEvent(ProductFormEvent.ProductNameChanged("yes"))},
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

                            )


                        Spacer(modifier = Modifier.height(20.dp))
                        TextField(value = "${products?.prodDesc}", onValueChange ={},
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


                            )


                        Spacer(modifier = Modifier.height(20.dp))
                        TextField(value = "${products?.prod_price}", onValueChange ={},
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


                            )


                        Spacer(modifier = Modifier.height(20.dp))
                        TextField(value = "${products?.prod_qty}", onValueChange ={},
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

                            )


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
                                Text(text = "Save Changes", color = colorResource(id = R.color.white), fontSize = 19.sp, textAlign = TextAlign.Center, modifier=Modifier.align(alignment = Alignment.Center))

                            }


                        }


                    }



                },

                title = {
                    Text(msg, textAlign = TextAlign.Center)
                },

                onDismissRequest = onDismiss,

                confirmButton = {
                    TextButton(onClick = onDismiss ) {
                        Text("Dismiss", color = colorResource(id = R.color.teal_700))
                    }
                },
                dismissButton = {}
            )






        }



    }
}














