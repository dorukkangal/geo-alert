package com.dorukkangal.geoalert.domain.repository.geoalert.model

import com.dorukkangal.geoalert.domain.repository.base.model.BaseItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GeoAlert(
    val id: Long,
    val name: String,
    val selected: Boolean,
    val location: LatLng
) : BaseItem() {

    override fun equals(other: Any?): Boolean = (other as? GeoAlert)?.id == id

    override fun hashCode(): Int = id.toInt()
}
