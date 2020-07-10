package com.dorukkangal.geoalert.ui.list

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukkangal.geoalert.R
import com.dorukkangal.geoalert.domain.util.distanceBetween
import com.dorukkangal.geoalert.domain.util.toLatLng
import com.dorukkangal.geoalert.ui.base.BaseMvvmFragment
import com.dorukkangal.geoalert.util.LocationHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_geo_alert_list.*
import javax.inject.Inject

@AndroidEntryPoint
class GeoAlertListFragment : BaseMvvmFragment<GeoAlertListViewModel>() {

    @Inject
    lateinit var locationHelper: LocationHelper

    override val viewModel: GeoAlertListViewModel by viewModels()

    private val geoAlertRecyclerAdapter by lazy {
        GeoAlertRecyclerAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_geo_alert_list

    override fun initViews() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getGeoAlerts()
        }

        with(recyclerViewGeoAlertList) {
            adapter = geoAlertRecyclerAdapter.apply {
                this.onItemSelectedListener = { position, selected ->
                    getItem(position)?.let {
                        viewModel.setSelected(it, selected)
                    }
                }

                setItemClickListener {
                    findNavController().navigate(GeoAlertListFragmentDirections.actionNewGeoAlert(it))
                }
            }

            layoutManager = LinearLayoutManager(context)
        }

        textViewNewGeoAlert.setOnClickListener {
            findNavController().navigate(GeoAlertListFragmentDirections.actionNewGeoAlert())
        }

        buttonLocation.setOnClickListener {
            viewModel.getSelectedGeoAlert()
        }
    }

    override fun onViewModelAttached() {
        viewModel.getGeoAlerts()
    }

    override fun observeEvents() {
        viewModel.geoAlerts.observe(this, Observer {
            geoAlertRecyclerAdapter.setItems(it.toList())
            swipeRefreshLayout.isRefreshing = false
        })

        viewModel.selectedGeoAlert.observe(this, Observer { geoAlert ->
            locationHelper.subscribeToLocationChanges { location ->
                val distance = location.toLatLng().distanceBetween(geoAlert.location)
                if (distance > 5f) {
                    Toast.makeText(context, "Distance: $distance", Toast.LENGTH_SHORT).show()
                } else {
                    findNavController().navigate(GeoAlertListFragmentDirections.actionProductList())
                }
            }
        })

        viewModel.updated.observe(this, Observer {
            if (it) {
                Toast.makeText(context, "Selection updated", Toast.LENGTH_SHORT).show()
                viewModel.updated.value = false
            }
        })
    }
}
