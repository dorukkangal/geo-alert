package com.dorukkangal.geoalert.domain.repository.product.model

import com.dorukkangal.geoalert.domain.repository.base.model.BaseItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val description: String
) : BaseItem()
