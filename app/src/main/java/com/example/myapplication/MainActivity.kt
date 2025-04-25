package com.example.myapplication

import WeatherViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myapplication.ui.screen.WeatherScreen
import com.example.myapplication.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                WeatherScreen(viewModel = viewModels<WeatherViewModel>().value)
            }
        }
    }
}