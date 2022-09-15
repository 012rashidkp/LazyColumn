package com.example.lazycolumn.Activity

import android.graphics.fonts.FontFamily
import android.os.Bundle
import android.text.Layout

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.semantics.SemanticsProperties.EditableText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily.Companion.Cursive
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.lazycolumn.Model.items
import com.example.lazycolumn.Navigation.Screens
import com.example.lazycolumn.Navigation.SetupNavGraph
import com.example.lazycolumn.R
import com.example.lazycolumn.ui.theme.AnimatedSplashScreenTheme
import com.example.lazycolumn.ui.theme.LazyColumnTheme
import com.example.lazycolumn.ui.theme.PlantsInCosmeticsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            AnimatedSplashScreenTheme {
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }

}




//Box(
//
//modifier = Modifier
//.fillMaxSize()
//.background(Color.White),
//contentAlignment = Alignment.Center
//) {
//    Allitems(items)
//}


