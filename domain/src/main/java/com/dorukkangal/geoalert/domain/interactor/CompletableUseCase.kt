package com.dorukkangal.geoalert.domain.interactor

import io.reactivex.Completable

abstract class CompletableUseCase<Params> : BaseUseCase<Unit, Params>() {

    abstract fun buildUseCase(params: Params?): Completable

    override fun execute(callback: ResponseCallback<Unit>?, params: Params?) {
        val completable = this.buildUseCase(params)
            .observeOn(postExecutionThread)
            .subscribeOn(executionThread)

        addDisposable(
            completable.doOnSubscribe {
                callback?.onStart()
            }.doFinally {
                callback?.onComplete()
            }.doOnComplete {
                callback?.onResponse(Unit)
            }.doOnError {
                callback?.onError(it as Error)
            }.subscribe({}, {})
        )
    }
}
