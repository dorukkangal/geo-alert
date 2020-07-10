package com.dorukkangal.geoalert.domain.repository.geoalert.usecase

import com.dorukkangal.geoalert.domain.interactor.MaybeUseCase
import com.dorukkangal.geoalert.domain.repository.geoalert.GeoAlertRepository
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import io.reactivex.Maybe
import javax.inject.Inject

class GetSelectedGeoAlert @Inject constructor(
    private val repository: GeoAlertRepository
) : MaybeUseCase<GeoAlert, Unit>() {

    override fun buildUseCase(params: Unit?): Maybe<GeoAlert> {
        return repository.getSelectedGeoAlert()
    }
}
