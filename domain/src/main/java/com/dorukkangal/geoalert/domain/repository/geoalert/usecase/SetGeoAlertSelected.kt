package com.dorukkangal.geoalert.domain.repository.geoalert.usecase

import com.dorukkangal.geoalert.domain.interactor.CompletableUseCase
import com.dorukkangal.geoalert.domain.repository.geoalert.GeoAlertRepository
import io.reactivex.Completable
import javax.inject.Inject

class SetGeoAlertSelected @Inject constructor(
    private val repository: GeoAlertRepository
) : CompletableUseCase<SetGeoAlertSelected.Params>() {

    override fun buildUseCase(params: Params?): Completable {
        return repository.setSelected(
            id = params!!.id,
            selected = params.selected
        )
    }

    class Params(
        val id: Long,
        val selected: Boolean = false
    )
}
