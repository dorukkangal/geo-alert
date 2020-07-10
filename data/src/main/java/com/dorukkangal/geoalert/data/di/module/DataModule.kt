package com.dorukkangal.geoalert.data.di.module

import com.dorukkangal.geoalert.data.store.geoalert.GeoAlertDataStore
import com.dorukkangal.geoalert.data.store.geoalert.GeoAlertLocalDataStore
import com.dorukkangal.geoalert.data.store.product.ProductDataStore
import com.dorukkangal.geoalert.data.store.product.ProductLocalDataStore
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindGeoAlertDataStore(dataStore: GeoAlertLocalDataStore): GeoAlertDataStore

    @Singleton
    @Binds
    fun bindProductDataStore(dataStore: ProductLocalDataStore): ProductDataStore
}
