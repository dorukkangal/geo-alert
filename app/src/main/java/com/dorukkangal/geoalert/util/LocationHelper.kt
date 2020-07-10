package com.dorukkangal.geoalert.util

import android.Manifest
import android.app.Activity
import android.location.Location
import android.os.Bundle
import com.dorukkangal.geoalert.domain.interactor.DisposableContainer
import com.yayandroid.locationmanager.LocationManager
import com.yayandroid.locationmanager.configuration.DefaultProviderConfiguration
import com.yayandroid.locationmanager.configuration.GooglePlayServicesConfiguration
import com.yayandroid.locationmanager.configuration.LocationConfiguration
import com.yayandroid.locationmanager.configuration.PermissionConfiguration
import com.yayandroid.locationmanager.listener.LocationListener
import dagger.hilt.android.scopes.ActivityScoped
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@ActivityScoped
class LocationHelper @Inject constructor(
    activity: Activity,
    private val disposableContainer: DisposableContainer
) : LocationListener {

    private val publisher = PublishSubject.create<Location>()

    private val locationConfiguration: LocationConfiguration by lazy {
        LocationConfiguration.Builder()
            .keepTracking(false)
            .askForPermission(
                PermissionConfiguration.Builder()
                    .requiredPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
                    .build()
            )
            .useGooglePlayServices(
                GooglePlayServicesConfiguration.Builder()
                    .askForGooglePlayServices(true)
                    .ignoreLastKnowLocation(true)
                    .build()
            )
            .useDefaultProviders(
                DefaultProviderConfiguration.Builder()
                    .build()
            )
            .build()
    }

    private val locationManager: LocationManager by lazy {
        LocationManager.Builder(activity.applicationContext)
            .activity(activity)
            .configuration(locationConfiguration)
            .notify(this)
            .build()
    }

    fun subscribeToLocationChanges(subscriber: (Location) -> Unit) {
        disposableContainer.addDisposable(
            publisher.subscribe({ subscriber(it) }, {})
        )
        locationManager.get()
    }

    override fun onLocationChanged(location: Location) {
        publisher.onNext(location)
    }

    override fun onLocationFailed(type: Int) = Unit

    override fun onPermissionGranted(alreadyHadPermission: Boolean) = Unit

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) = Unit

    override fun onProviderEnabled(provider: String?) = Unit

    override fun onProviderDisabled(provider: String?) = Unit

    override fun onProcessTypeChanged(processType: Int) = Unit
}
