package tw.edu.pu.s1071530.putour.ui.flower_spots

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import tw.edu.pu.s1071530.putour.R
import tw.edu.pu.s1071530.putour.data.flower_spots.Spot

class FlowerSpotsFragment : Fragment() {
    private lateinit var mSpots: ListView

    companion object {
        fun newInstance() = FlowerSpotsFragment()
    }

    private lateinit var viewModel: FlowerSpotsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.flower_spots_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSpots = view?.findViewById(R.id.spots)!!
        viewModel = ViewModelProvider(this).get(FlowerSpotsViewModel::class.java)
        viewModel.spots.observe(viewLifecycleOwner, Observer<List<Spot>> { spots -> setSpots(spots) })
    }

    private fun setSpots(spots: List<Spot>) {
        val spotsToDisplay = spots.filter { spot ->
            val areas = arrayOf("龍井區", "梧棲區", "清水區", "沙鹿區")
            areas.contains(spot.area)
        }
        val adapter =
            context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, spotsToDisplay.flatMap { spot -> listOf(spot.name) }) }
        mSpots.adapter = adapter
        mSpots.setOnItemClickListener { _, view, position, _ ->
            val targetSpot = spotsToDisplay[position]
            val bundle = Bundle()
            bundle.putParcelable("spot", targetSpot)
            bundle.putString("title", targetSpot.name)
            Navigation.findNavController(view).navigate(R.id.flowerSpotDetailFragment, bundle)
        }
    }
}
