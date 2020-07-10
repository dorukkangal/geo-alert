package com.dorukkangal.geoalert.domain.repository.geoalert.usecase

import com.dorukkangal.geoalert.domain.interactor.CompletableUseCase
import com.dorukkangal.geoalert.domain.repository.geoalert.GeoAlertRepository
import io.reactivex.Completable
import javax.inject.Inject

class CreateOrUpdateGeoAlert @Inject constructor(
    private val repository: GeoAlertRepository
) : CompletableUseCase<CreateOrUpdateGeoAlert.Params>() {

    override fun buildUseCase(params: Params?): Completable {
        return repository.saveOrUpdate(
            id = params!!.id,
            name = params.name,
            selected = params.selected,
            latitude = params.latitude,
            longitude = params.longitude
        )
    }

    class Params(
        val id: Long? = null,
        val name: String,
        val selected: Boolean = false,
        val latitude: Double,
        val longitude: Double
    )
}
