package tw.edu.pu.s1071530.putour.ui.i_bike

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

class IBikeFragment : Fragment() {
    private var showAll: Boolean = false
    private lateinit var mSpots: ListView

    companion object {
        fun newInstance() = IBikeFragment()
    }

    private lateinit var viewModel: IBikeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.i_bike_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mSpots = view?.findViewById(R.id.spots)!!
        showAll = arguments?.getBoolean("showAll")!!

        viewModel = ViewModelProvider(this).get(IBikeViewModel::class.java)
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
        val spotsDisplaying: ArrayList<Spot> = ArrayList()
        spots.flatMapTo(spotsToDisplay, { (_, spot) ->
            when {
                showAll -> {
                    listOf("${spot.geoArea}: ${spot.name}")
                }
                spot.geoArea == "沙鹿區" -> {
                    spotsDisplaying.add(spot)
                    listOf("${spot.name}: ${spot.description}")
                }
                else -> {
                    listOf()
                }
            }
        })
        setSpots(spotsToDisplay)
        mSpots.setOnItemClickListener { _, _, position, _ ->
            if (showAll)
                return@setOnItemClickListener
            val selectedSpot = spotsDisplaying[position]
            Toast.makeText(
                context,
                "場站：${selectedSpot.name}，剩餘 ${selectedSpot.currentAmount} 台腳踏車可以租借",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun setSpots(spots: List<String>) {
        val adapter =
            context?.let { ArrayAdapter<String>(it, android.R.layout.simple_list_item_1, spots) }
        mSpots.adapter = adapter
    }
}