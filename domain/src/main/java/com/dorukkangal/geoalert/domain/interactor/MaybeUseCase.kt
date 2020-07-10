package com.dorukkangal.geoalert.domain.interactor

import io.reactivex.Maybe

abstract class MaybeUseCase<T, Params> : BaseUseCase<T, Params>() {

    abstract fun buildUseCase(params: Params?): Maybe<T>

    override fun execute(callback: ResponseCallback<T>?, params: Params?) {
        val maybe = this.buildUseCase(params)
            .observeOn(postExecutionThread)
            .subscribeOn(executionThread)

        addDisposable(
            maybe.doOnSubscribe {
                callback?.onStart()
            }.doFinally {
                callback?.onComplete()
            }.doOnSuccess {
                callback?.onResponse(it)
            }.doOnError {
                callback?.onError(it as Error)
            }.subscribe({}, {})
        )
    }
}
