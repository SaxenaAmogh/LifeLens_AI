package com.example.lifelensai.view

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DRGauge(
    percentage: Int,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 12.dp
) {
    val sweepAngle = (percentage.coerceIn(0, 100) / 100f) * 270f // Arc covers 270°
    val startAngle = 135f // Start bottom-left, end bottom-right

    // Pick color based on 5 categories
    val gaugeColor = when (percentage) {
        in 0..20 -> Color(0xFF4CAF50)   // Class 0 (No DR) - Green
        in 21..40 -> Color(0xFF8BC34A)  // Class 1 (Mild) - Light Green
        in 41..60 -> Color(0xFFFFC107)  // Class 2 (Moderate) - Yellow
        in 61..80 -> Color(0xFFFF9800)  // Class 3 (Severe) - Orange
        else -> Color(0xFFF44336)       // Class 4 (Proliferative) - Red
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


@Composable
fun RiskCircle(
    percentage: Int,
    modifier: Modifier = Modifier,
    size: Dp = 170.dp
) {
    val circleColor = when (percentage) {
        in 0..40 -> Color(0xFF4CAF50) // Green
        in 41..70 -> Color(0xFFFFC107) // Yellow
        else -> Color(0xFFF44336) // Red
    }

    Column(
        modifier = modifier
            .size(size)
            .background(color = circleColor, shape = CircleShape),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Overall Risk",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
        Text(
            text = "$percentage%",
            color = Color.White,
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
    }
}