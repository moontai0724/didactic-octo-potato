package tw.edu.pu.s1071530.putour.data.ibike

import com.google.gson.annotations.SerializedName

class Spot {
    @SerializedName("sna")
    lateinit var name: String

    @SerializedName("ar")
    lateinit var description: String

    @SerializedName("sbi")
    var currentAmount: Int = 0

    @SerializedName("sarea")
    lateinit var geoArea: String
}
