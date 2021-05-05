package tw.edu.pu.s1071530.putour.ui.flower_spots.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import tw.edu.pu.s1071530.putour.R
import tw.edu.pu.s1071530.putour.data.flower_spots.Spot

class FlowerSpotDetailFragment : Fragment() {
    lateinit var mBack: Button
    lateinit var mArea: TextView
    lateinit var mName: TextView
    lateinit var mType: TextView
    lateinit var mTime: TextView

    companion object {
        fun newInstance() = FlowerSpotDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flower_spot_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mArea = view?.findViewById(R.id.area)!!
        mName = view?.findViewById(R.id.name)!!
        mType = view?.findViewById(R.id.type)!!
        mTime = view?.findViewById(R.id.time)!!
        mBack = view?.findViewById(R.id.back)!!
        mBack.setOnClickListener { activity?.onBackPressed() }

        val spot = arguments?.getParcelable<Spot>("spot")!!

        mArea.text = spot.area
        mName.text = spot.name
        mType.text = spot.type
        mTime.text = spot.time
    }

}