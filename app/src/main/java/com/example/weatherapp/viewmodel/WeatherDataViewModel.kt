import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherApiService
import com.example.weatherapp.model.WeatherData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel : ViewModel() {

    private val _weatherForecast = MutableLiveData<List<WeatherData>>()
    val weatherForecast: LiveData<List<WeatherData>> = _weatherForecast

    private val apiService = WeatherApiService.create()
    private val apiKey = "6f969fe2d1c61da1e7212cd11fcf7a7f"

    fun getWeatherForecast(zipCode: String) {
        viewModelScope.launch(IO) {
            val response = apiService.getWeatherForecast(zipCode, apiKey)
            withContext(Dispatchers.Main) {
                _weatherForecast.postValue(response.body()?.list)
            }
        }
    }
}

