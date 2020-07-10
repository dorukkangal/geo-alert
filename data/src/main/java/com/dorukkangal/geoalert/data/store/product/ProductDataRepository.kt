package com.dorukkangal.geoalert.data.store.product

import com.dorukkangal.geoalert.data.store.base.BaseDataRepository
import com.dorukkangal.geoalert.data.store.product.mapper.ProductMapper
import com.dorukkangal.geoalert.domain.repository.product.ProductRepository
import com.dorukkangal.geoalert.domain.repository.product.model.Product
import io.reactivex.Single
import javax.inject.Inject

class ProductDataRepository @Inject constructor(
    private val dataStore: ProductDataStore,
    private val mapper: ProductMapper
) : BaseDataRepository(), ProductRepository {

    override fun getAll(): Single<List<Product>> {
        return dataStore.getAll()
            .map {
                mapper.mapToItem(it).toList()
            }
            .first(emptyList())
    }
}
