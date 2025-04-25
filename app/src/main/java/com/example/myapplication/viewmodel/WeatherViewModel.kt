import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.myapplication.BuildConfig
import com.example.myapplication.data.model.WeatherResponse
import com.example.myapplication.data.remote.RetrofitClient
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.myapplication.data.model.CityInfo
import com.example.myapplication.data.remote.GeoRetrofitClient


class WeatherViewModel : ViewModel() {

    var weather by mutableStateOf<WeatherResponse?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var cityOptions by mutableStateOf<List<CityInfo>>(emptyList())
        private set

    fun fetchCityOptions(city: String) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            cityOptions = emptyList()

            try {
                val allCities = GeoRetrofitClient.api.getCityCoordinates(
                    city = city,
                    apiKey = BuildConfig.API_KEY
                )

                val seen = mutableSetOf<String>()
                val uniqueCities = allCities.filter {
                    val key = "${it.name}, ${it.country}"
                    seen.add(key)
                }

                if (uniqueCities.size == 1) {
                    getWeatherByCoordinates(uniqueCities[0])
                } else {
                    cityOptions = uniqueCities
                }

            } catch (e: Exception) {
                errorMessage = "Could not fetch cities: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun getWeatherByCoordinates(city: CityInfo) {
        viewModelScope.launch {
            isLoading = true
            errorMessage = null

            try {
                weather = RetrofitClient.api.getWeatherByCoordinates(
                    lat = city.lat,
                    lon = city.lon,
                    apiKey = BuildConfig.API_KEY
                )
            } catch (e: Exception) {
                errorMessage = "Could not load weather: ${e.message}"
            } finally {
                isLoading = false
                cityOptions = emptyList()
            }
        }
    }

}
