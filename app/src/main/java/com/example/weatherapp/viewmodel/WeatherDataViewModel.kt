import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.Network
import com.example.weatherapp.model.WeatherDataList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel(){
   /* private val currentWeatherDataSet = MutableLiveData<WeatherDataList>()
    fun getCurrentWeatherData(): LiveData<WeatherDataList> {
        return currentWeatherDataSet
    }*/
    private val weatherForecastDataSet = MutableLiveData<WeatherDataList>()
    fun getWeatherDataForecast(): LiveData<WeatherDataList> {
        return weatherForecastDataSet
    }

    fun getWeatherForecast(zip: String, units: String) {
        val baseApiUrl: String = "https://api.openweathermap.org/data/2.5/"
        val key: String = "6f969fe2d1c61da1e7212cd11fcf7a7f"
        val network = Network(baseApiUrl)
        network.initRetrofit().getWeatherForecast(zip, key, units)
            .enqueue(object : Callback<WeatherDataList> {
                override fun onResponse(
                    call: Call<WeatherDataList>,
                    response: Response<WeatherDataList>
                ) {
                    println("SUCCESS")
                    println(response.body().toString())
                    weatherForecastDataSet.value = response.body()
                }
                override fun onFailure(call: Call<WeatherDataList>, t: Throwable) {
                    println("FAILURE")
                    t.printStackTrace()
                }
            })
    }
}

