package com.dorukkangal.geoalert.ui.products

import android.view.ViewGroup
import com.dorukkangal.geoalert.R
import com.dorukkangal.geoalert.domain.repository.product.model.Product
import com.dorukkangal.geoalert.view.BaseRecyclerViewAdapter
import com.dorukkangal.geoalert.view.BaseRecyclerViewHolder
import kotlinx.android.synthetic.main.recycler_item_product.*

class ProductListRecyclerAdapter : BaseRecyclerViewAdapter<Product>() {
    override fun createNewViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerViewHolder<Product> = ProductViewHolder(parent)
}

private class ProductViewHolder(
    parent: ViewGroup
) : BaseRecyclerViewHolder<Product>(parent, R.layout.recycler_item_product) {

    override fun bindItem(item: Product) {
        textViewItemProductName.text = item.name
        textViewItemProductDetail.text = item.description
    }
}
