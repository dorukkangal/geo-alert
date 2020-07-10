package com.dorukkangal.geoalert.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dorukkangal.geoalert.domain.interactor.DisposableContainer
import com.dorukkangal.geoalert.domain.interactor.ResponseCallback
import io.reactivex.disposables.Disposable

abstract class BaseViewModel(
    protected val disposableContainer: DisposableContainer
) : ViewModel() {

    internal val loading = MutableLiveData<Boolean>()
    internal val error = MutableLiveData<Error>()

    override fun onCleared() {
        super.onCleared()
        disposableContainer.dispose()
    }

    open fun onViewAttached() = Unit

    open fun onViewDetached() = Unit

    internal fun addDisposable(disposable: Disposable) {
        disposableContainer.addDisposable(disposable)
    }

    internal fun <T> simpleResponseCallback(
        showLoading: Boolean = true,
        onStart: (() -> Unit)? = null,
        onComplete: (() -> Unit)? = null,
        onError: ((Error) -> Unit)? = null,
        onResponse: (T) -> Unit
    ): ResponseCallback<T> {
        return ResponseCallback(
            onStart = {
                if (onStart != null) {
                    onStart()
                } else {
                    if (showLoading) {
                        loading.value = true
                    }
                }
            },
            onResponse = {
                onResponse(it)
            },
            onError = {
                if (onError != null) {
                    onError(it)
                } else {
                    error.value = it
                }
            },
            onComplete = {
                if (onComplete != null) {
                    onComplete()
                } else {
                    if (showLoading) {
                        loading.value = false
                    }
                }
            }
        )
    }
}
