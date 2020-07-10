package com.dorukkangal.geoalert.util

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModelProvider
import com.dorukkangal.geoalert.ui.base.BaseMvvmActivity
import com.dorukkangal.geoalert.ui.base.BaseMvvmFragment
import com.dorukkangal.geoalert.ui.base.BaseViewModel
import java.lang.reflect.ParameterizedType

@MainThread
fun <VM : BaseViewModel> Any.viewModels(): VM {
    return when (this) {
        is BaseMvvmActivity<*> -> {
            ViewModelProvider(this.viewModelStore, this.defaultViewModelProviderFactory)
                .get(getViewModelClass<VM>())
        }
        is BaseMvvmFragment<*> -> {
            ViewModelProvider(this.viewModelStore, this.defaultViewModelProviderFactory)
                .get(getViewModelClass<VM>())
        }
        else -> throw RuntimeException()
    }
}

@Suppress("UNCHECKED_CAST")
private fun <VM : BaseViewModel> Any.getViewModelClass(): Class<VM> =
    (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.first() as Class<VM>
