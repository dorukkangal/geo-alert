package com.dorukkangal.geoalert.domain.repository.geoalert.model

import com.dorukkangal.geoalert.domain.repository.base.model.BaseItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LatLng(
    val latitude: Double,
    val longitude: Double
) : BaseItem()
