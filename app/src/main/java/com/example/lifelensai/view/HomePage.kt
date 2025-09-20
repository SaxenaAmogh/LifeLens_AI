package com.example.lifelensai.view

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavController) {

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
                                    Icons.Rounded.Menu,
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
                                            color = Color(0xFFE7E7E7)
                                        )
                                ){
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
                                            text = "Search Records",
                                            fontSize = 16.sp,
                                            color = Color.Black,
                                        )
                                        Icon(
                                            painter = painterResource(R.drawable.sort),
                                            contentDescription = "Menu",
                                            tint = PrimaryColor,
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
                        }
                        item {
                            Spacer(modifier = Modifier.size(0.04f * screenHeight))

                            repeat(6){
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
                                        )
                                ){
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                vertical = 13.dp,
                                                horizontal = 22.dp
                                            ),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Text(
                                            text = "Niranjan's Records",
                                            fontSize = 18.sp,
                                            color = Color.Black,
                                        )
                                        Text(
                                            text = "12 Sept, 2025",
                                            fontSize = 14.sp,
                                            color = PrimaryColor,
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(0.01 * screenHeight))
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                horizontal = 0.03 * screenWidth
                                            ),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        DRGauge(
                                            percentage = 78,
                                            modifier = Modifier
                                                .size(110.dp)
                                                .padding(12.dp)
                                        )
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "85%",
                                                fontSize = 50.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = Color.Black,
                                            )
                                            Text(
                                                text = "Overall Risk",
                                                fontSize = 14.sp,
                                                color = Color.Gray,
                                            )
                                        }
                                        DNGauge(
                                            percentage = 68,
                                            modifier = Modifier
                                                .size(110.dp)
                                                .padding(12.dp)
                                        )

                                    }
                                    Spacer(modifier = Modifier.height(0.02 * screenHeight))
                                }

                                Spacer(modifier = Modifier.size(0.02f * screenHeight))
                            }

                        }
                    }

                    //Add new record button
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .background(
                                shape = RoundedCornerShape(25),
                                color = PrimaryColor
                            )
                            .size(85.dp)
                            .clip(RoundedCornerShape(50))
                            .clickable(
                                onClick = {
                                    navController.navigate("select")
                                }
                            )
                    ){
                        Icon(
                            imageVector = Icons.Rounded.Add,
                            contentDescription = "Add",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun DRGauge(
    percentage: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 12.dp
) {
    val sweepAngle = (percentage.coerceIn(0, 100) / 100f) * 270f // Arc covers 270°
    val startAngle = 135f // Start bottom-left, end bottom-right

    // Pick color based on range
    val gaugeColor = when (percentage) {
        in 0..40 -> Color(0xFF4CAF50) // Green
        in 41..70 -> Color(0xFFFFC107) // Yellow
        else -> Color(0xFFF44336) // Red
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = size.minDimension
            val radius = diameter / 2f
            val topLeft = Offset((size.width - diameter) / 2, (size.height - diameter) / 2)
            val sizeArc = Size(diameter, diameter)

            // Background arc (light gray)
            drawArc(
                color = Color.LightGray,
                startAngle = startAngle,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = topLeft,
                size = sizeArc
            )

            // Progress arc
            drawArc(
                color = gaugeColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = topLeft,
                size = sizeArc
            )
        }

        // Center text
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$percentage%",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "DR Risk",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun DNGauge(
    percentage: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 12.dp
) {
    val sweepAngle = (percentage.coerceIn(0, 100) / 100f) * 270f // Arc covers 270°
    val startAngle = 135f // Start bottom-left, end bottom-right

    // Pick color based on range
    val gaugeColor = when (percentage) {
        in 0..40 -> Color(0xFF4CAF50) // Green
        in 41..70 -> Color(0xFFFFC107) // Yellow
        else -> Color(0xFFF44336) // Red
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val diameter = size.minDimension
            val radius = diameter / 2f
            val topLeft = Offset((size.width - diameter) / 2, (size.height - diameter) / 2)
            val sizeArc = Size(diameter, diameter)

            // Background arc (light gray)
            drawArc(
                color = Color.LightGray,
                startAngle = startAngle,
                sweepAngle = 270f,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = topLeft,
                size = sizeArc
            )

            // Progress arc
            drawArc(
                color = gaugeColor,
                startAngle = startAngle,
                sweepAngle = sweepAngle,
                useCenter = false,
                style = Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round),
                topLeft = topLeft,
                size = sizeArc
            )
        }

        // Center text
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "$percentage%",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "DN Risk",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePagePreview() {
    HomePage(rememberNavController())
}