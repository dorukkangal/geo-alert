package com.dorukkangal.geoalert.ui.products

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dorukkangal.geoalert.domain.interactor.DisposableContainer
import com.dorukkangal.geoalert.domain.repository.product.model.Product
import com.dorukkangal.geoalert.domain.repository.product.usecase.GetProducts
import com.dorukkangal.geoalert.ui.base.BaseViewModel

class ProductListViewModel @ViewModelInject constructor(
    private val getProducts: GetProducts,
    disposableContainer: DisposableContainer
) : BaseViewModel(disposableContainer) {

    internal val products = MutableLiveData<List<Product>>()

    internal fun getProducts() {
        getProducts.execute(
            simpleResponseCallback {
                products.value = it
            }
        )
    }
}
