package com.dorukkangal.geoalert.domain.repository.geoalert.usecase

import com.dorukkangal.geoalert.domain.interactor.CompletableUseCase
import com.dorukkangal.geoalert.domain.repository.geoalert.GeoAlertRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteGeoAlert @Inject constructor(
    private val repository: GeoAlertRepository
) : CompletableUseCase<DeleteGeoAlert.Params>() {

    override fun buildUseCase(params: Params?): Completable {
        return repository.delete(
            id = params!!.id
        )
    }

    class Params(
        val id: Long
    )
}
