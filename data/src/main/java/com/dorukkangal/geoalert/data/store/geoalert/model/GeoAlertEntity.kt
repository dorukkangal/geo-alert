package com.dorukkangal.geoalert.data.store.geoalert.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dorukkangal.geoalert.data.store.base.model.BaseEntity

@Entity(tableName = "geo_alert")
data class GeoAlertEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "selected") val selected: Boolean,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double
) : BaseEntity()
