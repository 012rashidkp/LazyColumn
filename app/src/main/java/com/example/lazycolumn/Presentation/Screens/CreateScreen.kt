package com.example.lazycolumn.Presentation.Screens

import android.Manifest
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.lazycolumn.Activity.MainActivity
import com.example.lazycolumn.FormValidation.ProductFormEvent
import com.example.lazycolumn.Presentation.Components.imagecontent
import com.example.lazycolumn.Presentation.Components.submitbutton
import com.example.lazycolumn.Presentation.Components.textfieldcomponent
import com.example.lazycolumn.Presentation.Components.tvcomponent
import com.example.lazycolumn.R
import com.example.lazycolumn.ViewModel.ProductDataViewModel
import com.example.lazycolumn.ViewModel.ProductValidationViewModel
import com.example.lazycolumn.ui.theme.Cyancolor
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

@OptIn(ExperimentalPermissionsApi::class)
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
    var prod_image by remember { mutableStateOf(ByteArray(0)) }

    var selecteduri by remember { mutableStateOf<Uri?>(null) }
    val producticon= painterResource(id = R.drawable.product)
    val descriptionicon= painterResource(id = R.drawable.description)
    val priceicon= painterResource(id = R.drawable.price)
    val quantityicon= painterResource(id = R.drawable.quantity)
    val buttoncolor= colorResource(id = R.color.teal_700)

    val galleryLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) {uri->
        if (uri != null) {
             selecteduri=uri
            productsavingviewmodel.onEvent(ProductFormEvent.ProductImageChanged(uri))





        }

    }
    val permissionState = rememberPermissionState(permission = Manifest.permission.READ_EXTERNAL_STORAGE)

    SideEffect {
        permissionState.launchPermissionRequest()
    }



    LaunchedEffect(key1 = context) {
        System.out.println("myimage... ${selecteduri}")
        productsavingviewmodel.validationEvents.collect { event ->
            when (event) {
                is ProductValidationViewModel.ValidationEvent.success -> {

                    CoroutineScope(Dispatchers.IO).launch {
                        kotlinx.coroutines.delay(3000)
                        withContext(Dispatchers.Main) {
                            (context as MainActivity).onBackPressed()
                        } }
                    loading.value = true



                    prod_image=getimagebyteArray(context,productsavingviewmodel.state.productimage)
                    prodName=productsavingviewmodel.state.productName
                    proddesc=productsavingviewmodel.state.productdesc
                    prodprice=productsavingviewmodel.state.productprice
                    prodqty=productsavingviewmodel.state.productqty

                    productDataViewModel.insertproducts(prodName,proddesc,prodprice.toDouble(),prodqty.toLong(),prod_image)


                }
            }
        }
    }






    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
   Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth(0.8f)) {

       Spacer(modifier = Modifier.height(10.dp))
       imagecontent(uri = selecteduri)
       if (state.productImageError!=null){
           Text(text = state.productImageError, color = MaterialTheme.colors.error)
       }

       Spacer(modifier = Modifier.height(10.dp))
       Text(
           text = "Choose Image",
           color = colorResource(id = R.color.teal_700),
           fontSize = 19.sp,
           textAlign = TextAlign.Center,
           modifier = Modifier.clickable {
               if (permissionState.status.isGranted){
                   galleryLauncher.launch("image/*")
               }
               else
                   permissionState.launchPermissionRequest()
           }
       )

       Spacer(modifier = Modifier.height(10.dp))


       textfieldcomponent(
           value = state.productName,
           changevalue ={
               productsavingviewmodel.onEvent(ProductFormEvent.ProductNameChanged(it))
           } ,
           placeholdertext ="Product Name",
           labeltext = "Product Name",
           keyboardType = KeyboardType.Text,
           icon=producticon,
           isError = state.productNameError!=null
       )

       if (state.productNameError!=null){
           Text(text = state.productNameError, color = MaterialTheme.colors.error)
       }
       Spacer(modifier = Modifier.height(20.dp))
       textfieldcomponent(
           value = state.productdesc,
           changevalue ={
               productsavingviewmodel.onEvent(ProductFormEvent.productdescChanged(it))
           } ,
           placeholdertext ="Product Desc",
           labeltext = "Product Desc",
           keyboardType = KeyboardType.Text,
           icon=descriptionicon,
           isError = state.productdescError!=null
       )

       if (state.productdescError!=null){
           Text(text = state.productdescError, color = MaterialTheme.colors.error)
       }

       Spacer(modifier = Modifier.height(20.dp))
       textfieldcomponent(
           value = state.productprice,
           changevalue ={
               productsavingviewmodel.onEvent(ProductFormEvent.productpricechanged(it))
           } ,
           placeholdertext ="Product Price",
           labeltext = "Product Price",
           keyboardType = KeyboardType.Decimal,
           icon=priceicon,
           isError = state.productpriceError!=null
       )

       if (state.productpriceError!=null){
           Text(text = state.productpriceError, color = MaterialTheme.colors.error)
       }

       Spacer(modifier = Modifier.height(20.dp))
       textfieldcomponent(
           value = state.productqty,
           changevalue ={
               productsavingviewmodel.onEvent(ProductFormEvent.productqtychanged(it))
           } ,
           placeholdertext ="Product Qty",
           labeltext = "Product Qty",
           keyboardType = KeyboardType.Number,
           icon=quantityicon,
           isError = state.productqtyError!=null
       )

       if (state.productqtyError!=null){
           Text(text = state.productqtyError, color = MaterialTheme.colors.error)
       }

       Spacer(modifier = Modifier.height(35.dp))
       submitbutton(
           onClick = {
               productsavingviewmodel.onEvent(ProductFormEvent.Saveproducts)
       },
           loading =loading.value,
           buttontxt = "Save Data"
       )

   }





    }



}

fun getimagebyteArray(context: Context, uri: Uri?): ByteArray {
    val contentResolver = context.contentResolver

    val inputStream = contentResolver.openInputStream(uri!!)
    val bitmap = BitmapFactory.decodeStream(inputStream)
    val selectedImage = bitmap.asImageBitmap()

    val outputStream = ByteArrayOutputStream()
    selectedImage.asAndroidBitmap().compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
    return outputStream.toByteArray()
}





