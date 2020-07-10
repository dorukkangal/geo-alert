package com.dorukkangal.geoalert.data.store.geoalert

import com.dorukkangal.geoalert.data.store.base.BaseLocalDataStore
import com.dorukkangal.geoalert.data.store.geoalert.model.GeoAlertEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class GeoAlertLocalDataStore @Inject constructor(
    private val dao: GeoAlertDao
) : BaseLocalDataStore(), GeoAlertDataStore {

    override fun getAll(): Flowable<List<GeoAlertEntity>> {
        return dao.findAll()
    }

    override fun getSelectedGeoAlert(): Maybe<GeoAlertEntity> {
        return dao.findSelectedGeoAlert()
    }

    override fun save(
        name: String,
        selected: Boolean,
        latitude: Double,
        longitude: Double
    ): Completable {
        return dao.insert(
            GeoAlertEntity(
                name = name,
                selected = selected,
                latitude = latitude,
                longitude = longitude
            )
        )
    }

    override fun update(
        id: Long,
        name: String,
        selected: Boolean,
        latitude: Double,
        longitude: Double
    ): Completable {
        return dao.update(
            GeoAlertEntity(
                id = id,
                name = name,
                selected = selected,
                latitude = latitude,
                longitude = longitude
            )
        )
    }

    override fun setSelected(id: Long, selected: Boolean): Completable {
        return dao.unselectAll()
            .andThen(dao.setSelected(id, selected))
    }

    override fun delete(id: Long): Completable {
        return dao.findById(id = id).flatMapCompletable {
            dao.delete(it)
        }
    }
}
