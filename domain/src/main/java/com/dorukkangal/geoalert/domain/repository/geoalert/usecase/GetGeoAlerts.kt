package com.dorukkangal.geoalert.domain.repository.geoalert.usecase

import com.dorukkangal.geoalert.domain.interactor.SingleUseCase
import com.dorukkangal.geoalert.domain.repository.geoalert.GeoAlertRepository
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import io.reactivex.Single
import javax.inject.Inject

class GetGeoAlerts @Inject constructor(
    private val repository: GeoAlertRepository
) : SingleUseCase<List<GeoAlert>, Unit>() {

    override fun buildUseCase(params: Unit?): Single<List<GeoAlert>> {
        return repository.getAll()
    }
}
