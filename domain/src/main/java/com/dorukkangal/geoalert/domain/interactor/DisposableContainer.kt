package com.dorukkangal.geoalert.domain.interactor

import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@ActivityScoped
class DisposableContainer @Inject constructor() {

    private val disposables = CompositeDisposable()

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        disposables.clear()
    }
}
