package com.dorukkangal.geoalert.domain.interactor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class BaseUseCase<T, Params> {

    protected val executionThread: Scheduler = Schedulers.io()
    protected val postExecutionThread: Scheduler = AndroidSchedulers.mainThread()

    @Inject
    lateinit var disposableContainer: DisposableContainer

    protected fun addDisposable(disposable: Disposable) {
        disposableContainer.addDisposable(disposable)
    }

    fun dispose() {
        disposableContainer.dispose()
    }

    abstract fun execute(callback: ResponseCallback<T>?, params: Params? = null)
}
