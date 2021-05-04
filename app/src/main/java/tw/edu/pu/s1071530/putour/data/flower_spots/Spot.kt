package tw.edu.pu.s1071530.putour.data.flower_spots

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class Spot() : Parcelable {
    @SerializedName("縣市")
    lateinit var county: String

    @SerializedName("行政區")
    lateinit var area: String

    @SerializedName("地點")
    lateinit var name: String

    @SerializedName("花種")
    lateinit var type: String

    @SerializedName("觀賞時期")
    lateinit var time: String

    constructor(parcel: Parcel) : this() {
        county = parcel.readString()!!
        area = parcel.readString()!!
        name = parcel.readString()!!
        type = parcel.readString()!!
        time = parcel.readString()!!
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(county)
        parcel.writeString(area)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Spot> {
        override fun createFromParcel(parcel: Parcel): Spot {
            return Spot(parcel)
        }

        override fun newArray(size: Int): Array<Spot?> {
            return arrayOfNulls(size)
        }
    }
}