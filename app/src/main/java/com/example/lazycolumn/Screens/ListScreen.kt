package com.example.lazycolumn.Screens

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.example.lazycolumn.Database.Products
import com.example.lazycolumn.FormValidation.ProductFormEvent
import com.example.lazycolumn.Model.DetailItems
import com.example.lazycolumn.Model.listitems
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.R
import com.example.lazycolumn.ViewModel.ItemViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalCoroutinesApi
@Composable
fun ListScreen(navController: NavHostController){
    val itemviewModel: ItemViewModel = hiltViewModel()


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
items(items = itemviewModel.listProducts){items->
  loadingcards(items,itemviewModel)
}
}
            }



        },

        )

}
@Composable
fun loadingcards(items: Products, itemviewModel: ItemViewModel) {
    val showDialog = remember { mutableStateOf(false) }



    if (showDialog.value) {
        alert(msg = "Product Details",
            itemviewModel,
            showDialog = showDialog.value,
            onDismiss = {showDialog.value = false})
    }


    Card(backgroundColor = colorResource(id = R.color.white),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                //dialog start

                showDialog.value = true
                itemviewModel.getproductsbyid(items.productid)
                //dialog end
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
                    color = Color.DarkGray,
                    fontSize = 19.sp
                )
                Text(
                    text =items.productdesc,
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                )
            }
            IconButton(onClick = {itemviewModel.deleteproduct(items)}) {
                Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription =null,
                    tint = colorResource(id = R.color.teal_700)
                )

            }


        }



    }

}
@Composable
fun alert(msg: String,itemviewModel: ItemViewModel,showDialog: Boolean, onDismiss: () -> Unit) {
    if (showDialog) {
        val loading = remember { mutableStateOf(false) }
        AlertDialog(
            backgroundColor = colorResource(id = R.color.white),
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {

                    Spacer(modifier = Modifier.height(20.dp))

                    TextField(
                        value ="${itemviewModel.listdetailproducts.value?.productName}", onValueChange = {},
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
                    TextField(value = "${itemviewModel.listdetailproducts.value?.productdesc}", onValueChange ={},
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
                    TextField(value = "${itemviewModel.listdetailproducts.value?.productprice}", onValueChange ={},
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
                    TextField(value = "${itemviewModel.listdetailproducts.value?.productqty}", onValueChange ={},
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














