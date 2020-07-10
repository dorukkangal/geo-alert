package com.dorukkangal.geoalert.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dorukkangal.geoalert.domain.interactor.DisposableContainer
import com.dorukkangal.geoalert.util.DialogHelper
import javax.inject.Inject

abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var disposableContainer: DisposableContainer

    @Inject
    lateinit var dialogHelper: DialogHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            initViews()
        }

        observeEvents()
    }

    override fun onDestroyView() {
        disposableContainer.dispose()
        super.onDestroyView()
    }

    /**
     * Get fragment's UI content layout resource id.
     *
     * @return The fragment's root view's resource id
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

    open fun showError(error: Error) {
        hideLoading()
        dialogHelper.showError(error.message)
    }
}
