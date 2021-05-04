package tw.edu.pu.s1071530.putour.ui.i_bike

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.edu.pu.s1071530.putour.data.ibike.ApiResponse
import tw.edu.pu.s1071530.putour.data.ibike.ApiService
import tw.edu.pu.s1071530.putour.data.ibike.Spot

class IBikeViewModel : ViewModel() {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://datacenter.taichung.gov.tw/swagger/OpenData/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var spots: MutableLiveData<Map<String, Spot>> = MutableLiveData()
    var error: MutableLiveData<String> = MutableLiveData()

    init {
        fetchSpots()
    }

    private fun fetchSpots() {
        val apiService = retrofit.create(ApiService::class.java)
        apiService.spots().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                spots.postValue(response.body()?.retVal)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.printStackTrace()
                error.postValue("Something went wrong")
            }
        })
    }
}
