package tw.edu.pu.s1071530.putour.data.ibike

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("34c2aa94-7924-40cc-96aa-b8d090f0ab69/")
    fun spots(): Call<ApiResponse>
}
