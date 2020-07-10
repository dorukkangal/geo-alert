package com.dorukkangal.geoalert.ui.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dorukkangal.geoalert.domain.interactor.DisposableContainer
import com.dorukkangal.geoalert.domain.repository.geoalert.usecase.CreateOrUpdateGeoAlert
import com.dorukkangal.geoalert.domain.repository.geoalert.usecase.DeleteGeoAlert
import com.dorukkangal.geoalert.ui.base.BaseViewModel

class NewGeoAlertViewModel @ViewModelInject constructor(
    private val createOrUpdateGeoAlert: CreateOrUpdateGeoAlert,
    private val deleteGeoAlert: DeleteGeoAlert,
    disposableContainer: DisposableContainer
) : BaseViewModel(disposableContainer) {

    internal val saved = MutableLiveData<Boolean>()
    internal val deleted = MutableLiveData<Boolean>()

    fun createOrUpdateGeoAlert(
        id: String?,
        name: String,
        latitude: Double,
        longitude: Double
    ) {
        saved.value = false
        createOrUpdateGeoAlert.execute(
            simpleResponseCallback {
                saved.value = true
            },
            CreateOrUpdateGeoAlert.Params(
                id = if (id?.isBlank() == true) null else id?.toLong(),
                name = name,
                latitude = latitude,
                longitude = longitude
            )
        )
    }

    fun deleteGeoAlert(id: String) {
        deleted.value = false
        deleteGeoAlert.execute(
            simpleResponseCallback {
                saved.value = true
            },
            DeleteGeoAlert.Params(id.toLong())
        )
    }
}
