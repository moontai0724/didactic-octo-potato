package tw.edu.pu.s1071530.putour.ui.taichung_ibike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import tw.edu.pu.s1071530.putour.R
import tw.edu.pu.s1071530.putour.data.ibike.Spot

class TaichungIBikeFragment : Fragment() {
    private lateinit var mSpots: ListView

    companion object {
        fun newInstance() = TaichungIBikeFragment()
    }

    private lateinit var viewModel: TaichungIBikeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.taichung_i_bike_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSpots = view?.findViewById(R.id.spots)!!
        viewModel = ViewModelProvider(this).get(TaichungIBikeViewModel::class.java)
        viewModel.spots
            .observe(viewLifecycleOwner, Observer<Map<String, Spot>> { spots -> setSpots(spots) })
        viewModel.error
            .observe(
                viewLifecycleOwner,
                Observer<String> { error ->
                    Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                })
    }

    private fun setSpots(spots: Map<String, Spot>) {
        val spotsToDisplay: ArrayList<String> = ArrayList()
        spots.flatMapTo(spotsToDisplay, { (_, spot) -> listOf("${spot.geoArea}: ${spot.name}") })
        setSpots(spotsToDisplay)
    }

    private fun setSpots(spots: List<String>) {
        val adapter =
            context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, spots) }
        mSpots.adapter = adapter
    }
}