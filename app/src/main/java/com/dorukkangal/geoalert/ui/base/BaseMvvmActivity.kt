package com.dorukkangal.geoalert.ui.base

import android.os.Bundle
import androidx.lifecycle.Observer
import com.dorukkangal.geoalert.util.viewModels

abstract class BaseMvvmActivity<VM : BaseViewModel> : BaseActivity() {

    protected val viewModel: VM by lazy { viewModels<VM>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.onViewAttached()
        onViewModelAttached()
    }

    override fun onDestroy() {
        onViewModelDetached()
        viewModel.onViewDetached()

        super.onDestroy()
    }

    override fun observeEvents() {
        super.observeEvents()

        with(viewModel) {
            loading.observe(this@BaseMvvmActivity, Observer { loading ->
                if (loading)
                    showLoading()
                else
                    hideLoading()
            })

            error.observe(this@BaseMvvmActivity, Observer { error ->
                showError(error)
            })
        }
    }

    protected open fun onViewModelAttached() {
        // Override this on child activities if needed.
    }

    protected open fun onViewModelDetached() {
        // Override this on child activities if needed.
    }
}
