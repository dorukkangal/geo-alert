package com.dorukkangal.geoalert.domain.interactor

open class ResponseCallback<T>(
    private val onStart: (() -> Unit)? = null,
    private val onComplete: (() -> Unit)? = null,
    private val onError: ((Error) -> Unit)? = null,
    private val onResponse: ((T) -> Unit)? = null
) {

    open fun onStart() {
        onStart?.invoke()
    }

    open fun onResponse(response: T) {
        onResponse?.invoke(response)
    }

    open fun onError(error: Error) {
        onError?.invoke(error)
    }

    open fun onComplete() {
        onComplete?.invoke()
    }
}
