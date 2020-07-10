package com.dorukkangal.geoalert.domain.repository.product

import com.dorukkangal.geoalert.domain.repository.base.BaseRepository
import com.dorukkangal.geoalert.domain.repository.product.model.Product
import io.reactivex.Single

interface ProductRepository : BaseRepository {

    fun getAll(): Single<List<Product>>
}
