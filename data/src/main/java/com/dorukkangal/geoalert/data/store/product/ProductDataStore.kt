package com.dorukkangal.geoalert.data.store.product

import com.dorukkangal.geoalert.data.store.base.BaseDataStore
import com.dorukkangal.geoalert.data.store.product.model.ProductEntity
import io.reactivex.Flowable

interface ProductDataStore : BaseDataStore {

    fun getAll(): Flowable<List<ProductEntity>>
}
