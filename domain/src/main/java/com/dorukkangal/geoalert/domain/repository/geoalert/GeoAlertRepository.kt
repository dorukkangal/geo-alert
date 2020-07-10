package com.dorukkangal.geoalert.domain.repository.geoalert

import com.dorukkangal.geoalert.domain.repository.base.BaseRepository
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface GeoAlertRepository : BaseRepository {

    fun getAll(): Single<List<GeoAlert>>

    fun getSelectedGeoAlert(): Maybe<GeoAlert>

    fun saveOrUpdate(
        id: Long?,
        name: String,
        selected: Boolean,
        latitude: Double,
        longitude: Double
    ): Completable

    fun setSelected(
        id: Long,
        selected: Boolean
    ): Completable

    fun delete(id: Long): Completable
}
