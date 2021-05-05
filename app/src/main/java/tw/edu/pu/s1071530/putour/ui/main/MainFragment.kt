package tw.edu.pu.s1071530.putour.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import tw.edu.pu.s1071530.putour.R

class MainFragment : Fragment() {
    private lateinit var mTaichungIBike: ImageButton
    private lateinit var mPUIBike: ImageButton
    private lateinit var mPUFlower: ImageButton
    private lateinit var mPUCheckIn: ImageButton
    private lateinit var mPULogout: ImageButton

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.mTaichungIBike = view?.findViewById(R.id.imageButton_taichung_ibike)!!
        this.mPUIBike = view?.findViewById(R.id.imageButton_pu_ibike)!!
        this.mPUFlower = view?.findViewById(R.id.imageButton_pu_flower)!!
        this.mPUCheckIn = view?.findViewById(R.id.imageButton_check_in)!!
        this.mPULogout = view?.findViewById(R.id.imageButton_logout)!!

        this.mTaichungIBike.setOnClickListener { v ->
            val bundle = Bundle()
            bundle.putBoolean("showAll", true)
            bundle.putString("title", getString(R.string.title_taichung_ibike))
            Navigation.findNavController(v).navigate(R.id.IBikeFragment, bundle)
        }

        this.mPUIBike.setOnClickListener { v ->
            val bundle = Bundle()
            bundle.putBoolean("showAll", false)
            bundle.putString("title", getString(R.string.title_pu_ibike))
            Navigation.findNavController(v).navigate(R.id.IBikeFragment, bundle)
        }

        this.mPUFlower.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.flowerSpotsFragment)
        }

        this.mPUCheckIn.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.checkInFragment)
        }

        this.mPULogout.setOnClickListener {
            activity?.finish()
        }
    }
}
