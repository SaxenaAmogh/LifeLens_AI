package com.example.lifelensai.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.lifelensai.R
import com.example.lifelensai.ui.theme.BackgroundColor
import com.example.lifelensai.ui.theme.PrimaryColor
import com.example.lifelensai.ui.theme.SecondaryColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PatientDetailsPage(navController: NavController) {


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

    var overallRisk by remember { mutableIntStateOf(70) }

    Scaffold(
        content = {
            Column(
                modifier = Modifier
                    .padding()
                    .fillMaxSize()
                    .background(BackgroundColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 0.035 * screenWidth,
                        )
                        .windowInsetsPadding(WindowInsets.systemBars)
                        .pointerInput(Unit) {
                            detectTapGestures(onTap = {
                                focusManager.clearFocus()
                            })
                        }
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter)
                    ) {
                        item {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = "Menu",
                                    tint = PrimaryColor,
                                    modifier = Modifier
                                        .size(32.dp)
                                )
                                Spacer(modifier = Modifier.size(0.03f * screenWidth))
                                Box(
                                    modifier = Modifier
                                        .weight(0.6f)
                                        .background(
                                            shape = RoundedCornerShape(50),
                                            color = Color(0xFFF5F5F5)
                                        )
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                vertical = 13.dp,
                                                horizontal = 22.dp
                                            ),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ){
                                        Text(
                                            text = "Niranjan's Records",
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                        Icon(
                                            painter = painterResource(R.drawable.sort),
                                            contentDescription = "Menu",
                                            tint = Color(0xFFF5F5F5),
                                            modifier = Modifier
                                                .size(32.dp)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.size(0.03f * screenWidth))
                                Image(
                                    painter = painterResource(R.drawable.mee),
                                    contentDescription = "Logo",
                                    modifier = Modifier
                                        .border(
                                            shape = RoundedCornerShape(50),
                                            width = 2.dp,
                                            color = PrimaryColor
                                        )
                                        .clip(RoundedCornerShape(50))
                                        .size(40.dp)
                                )
                            }
                            Spacer(modifier = Modifier.size(0.025 * screenHeight))
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        shape = RoundedCornerShape(10),
                                        color = Color(0xFFF5F5F5)
                                    )
                                    .border(
                                        shape = RoundedCornerShape(10),
                                        width = 1.dp,
                                        color = Color(0xFFD6D6D6)
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(0.02 * screenHeight))
                                Text(
                                    text = "Overall Micro Vascular Report",
                                    fontSize = 22.sp,
                                    color = PrimaryColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            vertical = 5.dp,
                                            horizontal = 35.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Absolute.SpaceAround
                                ) {
                                    Text(
                                        text = "Latest Record Dated",
                                        fontSize = 16.sp,
                                        color = Color.Gray,
                                    )
                                    Text(
                                        text = "12 Sept, 2025",
                                        fontSize = 15.sp,
                                        color = SecondaryColor,
                                        fontWeight = FontWeight.W500
                                    )
                                }
                                Spacer(modifier = Modifier.height(0.01 * screenHeight))
                                RiskCircle(percentage = 90)
                                Spacer(modifier = Modifier.height(0.02 * screenHeight))
                            }
                            Spacer(modifier = Modifier.size(0.015 * screenHeight))
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        shape = RoundedCornerShape(10),
                                        color = Color(0xFFF5F5F5)
                                    )
                                    .border(
                                        shape = RoundedCornerShape(10),
                                        width = 1.dp,
                                        color = Color(0xFFD6D6D6)
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Spacer(modifier = Modifier.height(0.02 * screenHeight))
                                Text(
                                    text = "Diabetic Retinopathy Analytics",
                                    fontSize = 20.sp,
                                    color = PrimaryColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(0.02 * screenHeight))
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = 0.05 * screenWidth,
                                            vertical = 0.02 * screenHeight
                                        ),
                                    horizontalArrangement = Arrangement.SpaceAround,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    DRGauge(
                                        percentage = 26,
                                        modifier = Modifier
                                            .size(0.3 * screenWidth)
                                    )
                                    Column {
                                        Row{
                                            Text(
                                                text = "Class: ",
                                                fontSize = 18.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "1 (Mild)",
                                                fontSize = 18.sp,
                                                color = SecondaryColor,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(0.01 * screenHeight))
                                        Row{
                                            Text(
                                                text = "Confidence: ",
                                                fontSize = 18.sp,
                                                color = Color.Black,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "26.73%",
                                                fontSize = 18.sp,
                                                color = SecondaryColor,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = "Classification Ranges",
                                    fontSize = 18.sp,
                                    color = PrimaryColor,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(0.02 * screenHeight))
                                Row(){
                                    Column {
                                        DRGauge(
                                            percentage = 0,
                                            modifier = Modifier
                                                .size(0.2 * screenWidth)
                                        )
                                        Text(
                                            text = "No DR",
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(0.05 * screenWidth))
                                    Column {
                                        DRGauge(
                                            percentage = 22,
                                            modifier = Modifier
                                                .size(0.2 * screenWidth)
                                        )
                                        Text(
                                            text = "Mild",
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(0.05 * screenWidth))
                                    Column {
                                        DRGauge(
                                            percentage = 41,
                                            modifier = Modifier
                                                .size(0.2 * screenWidth)
                                        )
                                        Text(
                                            text = "Moderate",
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.height(0.02 * screenHeight))
                                Row(){
                                    Column {
                                        DRGauge(
                                            percentage = 61,
                                            modifier = Modifier
                                                .size(0.2 * screenWidth)
                                        )
                                        Text(
                                            text = "Severe",
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(0.05 * screenWidth))
                                    Column {
                                        DRGauge(
                                            percentage = 81,
                                            modifier = Modifier
                                                .size(0.2 * screenWidth)
                                        )
                                        Text(
                                            text = "Proliferative",
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.size(0.015 * screenHeight))
                        }
                        item {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(
                                        shape = RoundedCornerShape(10),
                                        color = Color(0xFFF5F5F5)
                                    )
                                    .border(
                                        shape = RoundedCornerShape(10),
                                        width = 1.dp,
                                        color = Color(0xFFD6D6D6)
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PatientDetailsPagePreview() {
    PatientDetailsPage(rememberNavController())
}