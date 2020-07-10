package com.dorukkangal.geoalert.data.store.geoalert.mapper

import com.dorukkangal.geoalert.data.store.base.mapper.BaseMapper
import com.dorukkangal.geoalert.data.store.geoalert.model.GeoAlertEntity
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import com.dorukkangal.geoalert.domain.repository.geoalert.model.LatLng
import javax.inject.Inject

class GeoAlertMapper @Inject constructor() : BaseMapper<GeoAlertEntity, GeoAlert> {

    override fun mapToItem(data: GeoAlertEntity, vararg extra: Any?): GeoAlert {
        return GeoAlert(
            id = data.id!!,
            name = data.name,
            selected = data.selected,
            location = LatLng(data.latitude, data.longitude)
        )
    }
}
