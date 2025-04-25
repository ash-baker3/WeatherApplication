package com.example.myapplication.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.data.model.WeatherResponse
import kotlin.math.roundToInt

@Composable
fun WeatherDisplay(weather: WeatherResponse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "City: ${weather.name}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Temp: ${weather.main.temp.roundToInt()}°C",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = weather.weather.firstOrNull()?.description?.replaceFirstChar { it.uppercaseChar() }
                    ?: "",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            val icon = weather.weather.firstOrNull()?.icon
            if (icon != null) {
                val iconUrl = "https://openweathermap.org/img/wn/${icon}@2x.png"
                AsyncImage(model = iconUrl, contentDescription = null)
            }
        }
    }
}

