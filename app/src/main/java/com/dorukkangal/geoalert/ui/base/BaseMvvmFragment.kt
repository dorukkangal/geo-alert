package com.dorukkangal.geoalert.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer

abstract class BaseMvvmFragment<VM : BaseViewModel> : BaseFragment() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onViewAttached()
        onViewModelAttached()
    }

    override fun onDestroyView() {
        onViewModelDetached()
        viewModel.onViewDetached()

        super.onDestroyView()
    }

    override fun observeEvents() {
        super.observeEvents()

        with(viewModel) {
            loading.observe(this@BaseMvvmFragment, Observer { loading ->
                if (loading)
                    showLoading()
                else
                    hideLoading()
            })

            error.observe(this@BaseMvvmFragment, Observer { error ->
                showError(error)
            })
        }
    }

    protected open fun onViewModelAttached() {
        // Override this on child fragments if needed.
    }

    protected open fun onViewModelDetached() {
        // Override this on child fragments if needed.
    }
}
