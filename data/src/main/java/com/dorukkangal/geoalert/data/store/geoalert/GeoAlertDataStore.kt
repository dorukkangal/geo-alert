package com.dorukkangal.geoalert.data.store.geoalert

import com.dorukkangal.geoalert.data.store.base.BaseDataStore
import com.dorukkangal.geoalert.data.store.geoalert.model.GeoAlertEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

interface GeoAlertDataStore : BaseDataStore {

    fun getAll(): Flowable<List<GeoAlertEntity>>

    fun getSelectedGeoAlert(): Maybe<GeoAlertEntity>

    fun save(
        name: String,
        selected: Boolean,
        latitude: Double,
        longitude: Double
    ): Completable

    fun update(
        id: Long,
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
