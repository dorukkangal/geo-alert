package com.dorukkangal.geoalert.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.dorukkangal.geoalert.domain.interactor.DisposableContainer
import com.dorukkangal.geoalert.domain.repository.geoalert.model.GeoAlert
import com.dorukkangal.geoalert.domain.repository.geoalert.usecase.GetGeoAlerts
import com.dorukkangal.geoalert.domain.repository.geoalert.usecase.GetSelectedGeoAlert
import com.dorukkangal.geoalert.domain.repository.geoalert.usecase.SetGeoAlertSelected
import com.dorukkangal.geoalert.ui.base.BaseViewModel


class GeoAlertListViewModel @ViewModelInject constructor(
    private val getGeoAlerts: GetGeoAlerts,
    private val getSelectedGeoAlert: GetSelectedGeoAlert,
    private val setGeoAlertSelected: SetGeoAlertSelected,
    disposableContainer: DisposableContainer
) : BaseViewModel(disposableContainer) {

    internal val geoAlerts = MutableLiveData<List<GeoAlert>>()
    internal val selectedGeoAlert = MutableLiveData<GeoAlert>()
    internal val updated = MutableLiveData<Boolean>()

    internal fun getGeoAlerts() {
        getGeoAlerts.execute(
            simpleResponseCallback {
                geoAlerts.value = it
            }
        )
    }

    fun setSelected(geoAlert: GeoAlert, selected: Boolean) {
        setGeoAlertSelected.execute(
            simpleResponseCallback {
                updated.value = true
            },
            SetGeoAlertSelected.Params(
                id = geoAlert.id,
                selected = selected
            )
        )
    }

    fun getSelectedGeoAlert() {
        getSelectedGeoAlert.execute(
            simpleResponseCallback {
                selectedGeoAlert.value = it
            }
        )
    }
}
