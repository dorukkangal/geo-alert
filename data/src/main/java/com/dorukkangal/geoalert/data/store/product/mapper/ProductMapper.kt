package com.dorukkangal.geoalert.data.store.product.mapper

import com.dorukkangal.geoalert.data.store.base.mapper.BaseMapper
import com.dorukkangal.geoalert.data.store.product.model.ProductEntity
import com.dorukkangal.geoalert.domain.repository.product.model.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() : BaseMapper<ProductEntity, Product> {

    override fun mapToItem(data: ProductEntity, vararg extra: Any?): Product {
        return Product(
            id = data.id,
            name = data.name,
            description = data.description
        )
    }
}
