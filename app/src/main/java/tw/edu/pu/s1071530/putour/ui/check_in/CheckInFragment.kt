package tw.edu.pu.s1071530.putour.ui.check_in

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.s1071530.putour.R
import tw.edu.pu.s1071530.putour.data.flower_spots.Spot
import tw.edu.pu.s1071530.putour.ui.flower_spots.FlowerSpotsViewModel

class CheckInFragment : Fragment() {
    val REQUEST_IMAGE_CAPTURE = 1
    lateinit var mImageView: ImageView

    companion object {
        fun newInstance() = CheckInFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_check_in, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mImageView = view?.findViewById(R.id.result)!!
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            mImageView.setImageBitmap(imageBitmap)
            Toast.makeText(context, "打卡成功！", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "取消拍照打卡", Toast.LENGTH_LONG).show()
        }
    }
}