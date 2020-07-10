package com.dorukkangal.geoalert.data.store.geoalert

import com.dorukkangal.geoalert.data.store.base.BaseDataRepository
import com.dorukkangal.geoalert.data.store.geoalert.mapper.GeoAlertMapper
import com.dorukkangal.geoalert.domain.repository.geoalert.GeoAlertRepository
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import javax.inject.Inject

class GeoAlertDataRepository @Inject constructor(
    private val dataStore: GeoAlertDataStore,
    private val mapper: GeoAlertMapper
) : BaseDataRepository(), GeoAlertRepository {

    override fun getAll(): Single<List<GeoAlert>> {
        return dataStore.getAll()
            .map {
                mapper.mapToItem(it).toList()
            }
            .first(emptyList())
    }

    override fun getSelectedGeoAlert(): Maybe<GeoAlert> {
        return dataStore.getSelectedGeoAlert()
            .map {
                mapper.mapToItem(it)
            }
    }

    override fun saveOrUpdate(
        id: Long?,
        name: String,
        selected: Boolean,
        latitude: Double,
        longitude: Double
    ): Completable {
        return when (id) {
            null -> dataStore.save(name, selected, latitude, longitude)
            else -> dataStore.update(id, name, selected, latitude, longitude)
        }
    }

    override fun setSelected(id: Long, selected: Boolean): Completable {
        return dataStore.setSelected(id, selected)
    }

    override fun delete(id: Long): Completable {
        return dataStore.delete(id)
    }
}
