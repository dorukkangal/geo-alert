package com.dorukkangal.geoalert.data.store.product.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dorukkangal.geoalert.data.store.base.model.BaseEntity

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String
) : BaseEntity()
