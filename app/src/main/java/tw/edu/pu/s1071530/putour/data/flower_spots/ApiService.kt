package tw.edu.pu.s1071530.putour.data.flower_spots

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("f116d1db-56f7-4984-bad8-c82e383765c0/")
    fun spots(): Call<List<Spot>>
}
