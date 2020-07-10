package com.dorukkangal.geoalert.data.di.module

import com.dorukkangal.geoalert.data.store.geoalert.GeoAlertDataRepository
import com.dorukkangal.geoalert.data.store.product.ProductDataRepository
import com.dorukkangal.geoalert.domain.repository.geoalert.GeoAlertRepository
import com.dorukkangal.geoalert.domain.repository.product.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface DomainModule {

    @Singleton
    @Binds
    fun bindGeoAlertDataRepository(dataRepository: GeoAlertDataRepository): GeoAlertRepository

    @Singleton
    @Binds
    fun bindProductDataRepository(dataRepository: ProductDataRepository): ProductRepository
}
