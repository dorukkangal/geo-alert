package com.dorukkangal.geoalert.domain.repository.product.usecase

import com.dorukkangal.geoalert.domain.interactor.SingleUseCase
import com.dorukkangal.geoalert.domain.repository.product.ProductRepository
import com.dorukkangal.geoalert.domain.repository.product.model.Product
import io.reactivex.Single
import javax.inject.Inject

class GetProducts @Inject constructor(
    private val repository: ProductRepository
) : SingleUseCase<List<Product>, Unit>() {

    override fun buildUseCase(params: Unit?): Single<List<Product>> {
        return repository.getAll()
    }
}
