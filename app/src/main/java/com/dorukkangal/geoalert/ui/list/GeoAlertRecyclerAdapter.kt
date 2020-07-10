package com.dorukkangal.geoalert.ui.list

import android.view.ViewGroup
import com.dorukkangal.geoalert.R
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import com.dorukkangal.geoalert.view.BaseRecyclerViewAdapter
import com.dorukkangal.geoalert.view.BaseRecyclerViewHolder
import kotlinx.android.synthetic.main.recycler_item_geo_alert.*

class GeoAlertRecyclerAdapter : BaseRecyclerViewAdapter<GeoAlert>() {

    internal lateinit var onItemSelectedListener: (Int, Boolean) -> Unit

    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<GeoAlert> = GeoAlertViewHolder(parent, onItemSelectedListener)
}

private class GeoAlertViewHolder(
    parent: ViewGroup,
    private val onItemSelectedListener: (Int, Boolean) -> Unit
) : BaseRecyclerViewHolder<GeoAlert>(parent, R.layout.recycler_item_geo_alert) {

    override fun bindItem(item: GeoAlert) {
        textViewId.text = item.id.toString()
        textViewName.text = item.name

        with(checkBoxSelected) {
            setOnCheckedChangeListener(null)
            isChecked = item.selected

            setOnCheckedChangeListener { _, isChecked ->
                onItemSelectedListener(adapterPosition, isChecked)
            }
        }

        textViewLatitude.text = item.location.latitude.toString()
        textViewLongitude.text = item.location.longitude.toString()
    }
}
