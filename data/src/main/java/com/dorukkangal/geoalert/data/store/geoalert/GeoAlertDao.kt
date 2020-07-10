package com.dorukkangal.geoalert.data.store.geoalert

import androidx.room.*
import com.dorukkangal.geoalert.data.store.geoalert.model.GeoAlertEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface GeoAlertDao {
    @Query("SELECT * FROM geo_alert")
    fun findAll(): Flowable<List<GeoAlertEntity>>

    @Query("SELECT * FROM geo_alert WHERE id=:id")
    fun findById(id: Long): Maybe<GeoAlertEntity>

    @Query("SELECT * FROM geo_alert WHERE selected=1 LIMIT 1")
    fun findSelectedGeoAlert(): Maybe<GeoAlertEntity>

    @Insert
    fun insert(geoAlert: GeoAlertEntity): Completable

    @Update
    fun update(geoAlert: GeoAlertEntity): Completable

    @Query("UPDATE geo_alert SET selected=:selected WHERE id=:id")
    fun setSelected(id: Long, selected: Boolean): Completable

    @Query("UPDATE geo_alert SET selected=0 WHERE selected=1")
    fun unselectAll(): Completable

    @Delete
    fun delete(geoAlert: GeoAlertEntity): Completable
}
