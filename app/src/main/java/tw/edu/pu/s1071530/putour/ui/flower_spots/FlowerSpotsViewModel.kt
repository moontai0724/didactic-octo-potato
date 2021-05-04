package tw.edu.pu.s1071530.putour.ui.flower_spots

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.edu.pu.s1071530.putour.data.flower_spots.ApiService
import tw.edu.pu.s1071530.putour.data.flower_spots.Spot

class FlowerSpotsViewModel : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://datacenter.taichung.gov.tw/swagger/OpenData/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var spots: MutableLiveData<List<Spot>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    init {
        fetchSpots()
    }

    private fun fetchSpots() {
        val apiService = retrofit.create(ApiService::class.java)
        apiService.spots().enqueue(object : Callback<List<Spot>> {
            override fun onResponse(call: Call<List<Spot>>, response: Response<List<Spot>>) {
                spots.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Spot>>, t: Throwable) {
                t.printStackTrace()
                error.postValue("Something went wrong")
            }
        })
    }
}