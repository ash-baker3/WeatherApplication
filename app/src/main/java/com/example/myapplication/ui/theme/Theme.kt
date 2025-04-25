package com.example.myapplication.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun WeatherAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF6200EE),
            secondary = LightBlue,
            background = LightBlue,
            surface = Color.White,  // Surface color
            onPrimary = Color.White,  // Text color on primary
            onSecondary = Color.Black,  // Text color on secondary
            onBackground = Color.Black,  // Text color on background
            onSurface = Color.Black,  // Text color on surface
        ),
        typography = Typography(
            headlineLarge = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            ),
            bodyLarge = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            ),
            bodyMedium = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            ),
            titleLarge = TextStyle(
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        ),
        shapes = Shapes(
            small = RoundedCornerShape(4.dp),
            medium = RoundedCornerShape(8.dp),
            large = RoundedCornerShape(16.dp)
        ),
        content = content
    )
}
