package com.dorukkangal.geoalert.ui.products

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dorukkangal.geoalert.R
import com.dorukkangal.geoalert.ui.base.BaseMvvmFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_list.*

@AndroidEntryPoint
class ProductListFragment : BaseMvvmFragment<ProductListViewModel>() {

    override val viewModel: ProductListViewModel by viewModels()

    private val productListRecyclerAdapter by lazy {
        ProductListRecyclerAdapter()
    }

    override fun getLayoutId(): Int = R.layout.fragment_product_list

    override fun initViews() {
        with(recyclerViewProductList) {
            adapter = productListRecyclerAdapter

            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onViewModelAttached() {
        viewModel.getProducts()
    }

    override fun observeEvents() {
        viewModel.products.observe(this, Observer {
            productListRecyclerAdapter.setItems(it)
        })
    }
}
