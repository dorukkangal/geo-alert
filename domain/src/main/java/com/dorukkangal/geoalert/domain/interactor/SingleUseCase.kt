package com.dorukkangal.geoalert.domain.interactor

import io.reactivex.Single

abstract class SingleUseCase<T, Params> : BaseUseCase<T, Params>() {

    abstract fun buildUseCase(params: Params?): Single<T>

    override fun execute(callback: ResponseCallback<T>?, params: Params?) {
        val single = this.buildUseCase(params)
            .observeOn(postExecutionThread)
            .subscribeOn(executionThread)

        addDisposable(
            single.doOnSubscribe {
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
