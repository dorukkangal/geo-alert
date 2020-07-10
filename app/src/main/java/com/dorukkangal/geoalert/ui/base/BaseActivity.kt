package com.dorukkangal.geoalert.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dorukkangal.geoalert.domain.interactor.DisposableContainer
import com.dorukkangal.geoalert.util.DialogHelper
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var disposableContainer: DisposableContainer

    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getLayoutId())

        if (savedInstanceState == null) {
            initViews()
        }

        observeEvents()
    }

    override fun onDestroy() {
        disposableContainer.dispose()
        super.onDestroy()
    }

    /**
     * Get activity's UI content layout resource id.
     *
     * @return The activity's content's resource id
     */
    protected abstract fun getLayoutId(): Int

    /**
     * Initialize UI content elements.
     */
    protected abstract fun initViews()

    /**
     * Observe live data.
     */
    protected open fun observeEvents() = Unit

    open fun showLoading() {
        dialogHelper.showLoadingDialog()
    }

    open fun hideLoading() {
        dialogHelper.dismissLoadingDialog()
    }

    open fun showError(error: Throwable) {
        hideLoading()
        dialogHelper.showError(error.message)
    }
}
