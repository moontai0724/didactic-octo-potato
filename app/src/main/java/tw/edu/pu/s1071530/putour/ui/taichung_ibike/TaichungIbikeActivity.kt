package tw.edu.pu.s1071530.putour.ui.taichung_ibike

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tw.edu.pu.s1071530.putour.R
import tw.edu.pu.s1071530.putour.data.ibike.ApiResponse
import tw.edu.pu.s1071530.putour.data.ibike.ApiService
import tw.edu.pu.s1071530.putour.data.ibike.Spot

class TaichungIbikeActivity : AppCompatActivity() {
    private lateinit var mSpots: ListView
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://datacenter.taichung.gov.tw/swagger/OpenData/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_taichung_ibike)
        mSpots = findViewById(R.id.spots)

        setSpots(listOf("Loading..."))

        val apiService = retrofit.create(ApiService::class.java)
        apiService.spots().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                val spots = response.body()?.retVal
                if (spots.isNullOrEmpty())
                    setSpots(listOf("No data"))
                else
                    setSpots(spots)
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                t.printStackTrace()
                setSpots(listOf("Some error occurred"))
            }
        })
    }

    fun setSpots(spots: Map<String, Spot>) {
        val spotsToDisplay: ArrayList<String> = ArrayList()
        spots.flatMapTo(spotsToDisplay, { (_, spot) -> listOf("${spot.geoArea}: ${spot.name}") })
        setSpots(spotsToDisplay)
    }

    fun setSpots(spots: List<String>) {
        val adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spots)
        mSpots.adapter = adapter
    }
}
