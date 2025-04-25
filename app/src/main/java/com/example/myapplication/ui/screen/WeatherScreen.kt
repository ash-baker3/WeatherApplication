package com.example.myapplication.ui.screen

import WeatherViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.components.WeatherDisplay

@Composable
fun WeatherScreen(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Weather Application",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                label = { Text("Enter city") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { viewModel.fetchCityOptions(city) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Find City")
            }

            Spacer(modifier = Modifier.height(24.dp))

            when {
                viewModel.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.size(40.dp))
                }
                viewModel.errorMessage != null -> {
                    Text(
                        viewModel.errorMessage ?: "Error",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
                viewModel.cityOptions.isNotEmpty() -> {
                    Text("Select a city:", style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(8.dp))
                    viewModel.cityOptions.forEach { cityInfo ->
                        Button(
                            onClick = { viewModel.getWeatherByCoordinates(cityInfo) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                        ) {
                            Text("${cityInfo.name}, ${cityInfo.country}")
                        }
                    }
                }
                viewModel.weather != null -> {
                    Card(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(8.dp)
                    ) {
                        WeatherDisplay(weather = viewModel.weather!!)
                    }
                }
            }
        }
    }
}
