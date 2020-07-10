package com.dorukkangal.geoalert.domain.util

import android.location.Location
import com.dorukkangal.geoalert.domain.repository.geoalert.model.LatLng

fun LatLng?.distanceBetween(from: LatLng?): Float {
    if (this == null || from == null) {
        return 0f
    }

    val distance = FloatArray(1)
    Location.distanceBetween(this.latitude, this.longitude, from.latitude, from.longitude, distance)
    return distance[0]
}

fun Location?.toLatLng(): LatLng? {
    return if (this == null) {
        null
    } else {
        LatLng(this.latitude, this.longitude)
    }
}
