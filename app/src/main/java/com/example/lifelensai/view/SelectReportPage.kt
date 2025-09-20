package com.example.lifelensai.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifelensai.R
import com.example.lifelensai.ui.theme.BackgroundColor
import com.example.lifelensai.ui.theme.PrimaryColor

@Composable
fun SelectReportPage(navController: NavController) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    val focusManager = LocalFocusManager.current

    val view = LocalView.current
    val window = (view.context as? Activity)?.window
    val windowInsetsController = window?.let { WindowCompat.getInsetsController(it, view) }
    if (windowInsetsController != null) {
        windowInsetsController.isAppearanceLightStatusBars = true
    }

    Scaffold(
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxSize()
                    .background(BackgroundColor)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(WindowInsets.systemBars.only(WindowInsetsSides.Bottom))
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                focusManager.clearFocus()
                            })
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                PrimaryColor
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "Micro Vascular Reports",
                            fontSize = 26.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier
                                .padding(top = innerPadding.calculateTopPadding())
                                .padding(
                                    top = 20.dp,
                                    bottom = 30.dp
                                )

                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = screenWidth * 0.05f,
                                vertical = 30.dp
                            )
                            .background(
                                shape = RoundedCornerShape(12),
                                color = Color(0xFFF3F3F3)
                            )
                    ) {
                        Text(
                            text = "About LifeLens AI",
                            fontSize = 22.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                        Text(
                            text = "Upload your patient's data to get full risk assessment for diabetic retinopathy and nephropathy, to get a holistic report on micro vascular risk score.",
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W400,
                            modifier = Modifier
                                .padding(10.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(0.05 * screenHeight))
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = screenWidth * 0.05f
                            )
                            .fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    shape = RoundedCornerShape(12),
                                    color = Color(0xFF3d75ec)
                                )
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 20.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.CenterVertically)
                                        .background(
                                            shape = RoundedCornerShape(50),
                                            color = Color(0x4AF3F3F3)
                                        )
                                ){
                                    Icon(
                                        painter = painterResource(R.drawable.eye),
                                        contentDescription = "View Report",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(12.dp)
                                    )
                                }
                                Text(
                                    text = "Diabetic Retinopathy",
                                    fontSize = 26.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                            }
                            Text(
                                text = "Upload fundus image to get the AI powered report of Diabetic Retinopathy.",
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.W400,
                                modifier = Modifier
                                    .padding(
                                        horizontal = 20.dp,
                                    )
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    shape = RoundedCornerShape(12),
                                    color = Color(0xFF0fb8d5)
                                )
                        ){
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = 10.dp,
                                        vertical = 20.dp
                                    ),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.CenterVertically)
                                        .background(
                                            shape = RoundedCornerShape(50),
                                            color = Color(0x4AF3F3F3)
                                        )
                                ){
                                    Icon(
                                        painter = painterResource(R.drawable.eye),
                                        contentDescription = "View Report",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(12.dp)
                                    )
                                }
                                Text(
                                    text = "Diabetic Nephropathy",
                                    fontSize = 26.sp,
                                    color = Color.White,
                                    fontWeight = FontWeight.W500,
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                )
                            }
                            Text(
                                text = "Upload fundus image to get the AI powered report of Diabetic Retinopathy.",
                                fontSize = 16.sp,
                                color = Color.White,
                                fontWeight = FontWeight.W400,
                                modifier = Modifier
                                    .padding(
                                        horizontal = 20.dp,
                                    )
                            )
                            Spacer(modifier = Modifier.padding(10.dp))
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SelectReportPagePreview() {
    SelectReportPage(rememberNavController())
}