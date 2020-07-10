package com.dorukkangal.geoalert.domain.interactor

import io.reactivex.Flowable

abstract class FlowableUseCase<T, Params> : BaseUseCase<T, Params>() {

    abstract fun buildUseCase(params: Params?): Flowable<T>

    override fun execute(callback: ResponseCallback<T>?, params: Params?) {
        val flowable = this.buildUseCase(params)
            .observeOn(postExecutionThread)
            .subscribeOn(executionThread)

        addDisposable(
            flowable.doOnSubscribe {
                callback?.onStart()
            }.doOnComplete {
                callback?.onComplete()
            }.doOnNext {
                callback?.onResponse(it)
            }.doOnError {
                callback?.onError(it as Error)
            }.subscribe({}, {})
        )
    }
}
