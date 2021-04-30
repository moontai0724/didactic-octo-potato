package tw.edu.pu.s1071530.putour.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import tw.edu.pu.s1071530.putour.R

class MainActivity : AppCompatActivity() {
    private lateinit var mTaichungIBike: ImageButton
    private lateinit var mPUIBike: ImageButton
    private lateinit var mPUFlower: ImageButton
    private lateinit var mPUCheckIn: ImageButton
    private lateinit var mPULogout: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.mTaichungIBike = findViewById(R.id.imageButton_taichung_ibike)
        this.mPUIBike = findViewById(R.id.imageButton_pu_ibike)
        this.mPUFlower = findViewById(R.id.imageButton_pu_flower)
        this.mPUCheckIn = findViewById(R.id.imageButton_check_in)
        this.mPULogout = findViewById(R.id.imageButton_logout)

        mPULogout.setOnClickListener {
            finish()
        }
    }
}