package com.dorukkangal.geoalert.data.store.product

import com.dorukkangal.geoalert.data.store.base.BaseLocalDataStore
import com.dorukkangal.geoalert.data.store.product.model.ProductEntity
import io.reactivex.Flowable
import javax.inject.Inject

class ProductLocalDataStore @Inject constructor(
    private val dao: ProductDao
) : BaseLocalDataStore(), ProductDataStore {

    override fun getAll(): Flowable<List<ProductEntity>> {
        return dao.findAll()
    }
}
