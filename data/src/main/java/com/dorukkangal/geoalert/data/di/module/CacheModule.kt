package com.dorukkangal.geoalert.data.di.module

import android.content.Context
import com.dorukkangal.geoalert.data.cache.AppDatabase
import com.dorukkangal.geoalert.data.store.geoalert.GeoAlertDao
import com.dorukkangal.geoalert.data.store.product.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.buildDatabase(context)

    @Singleton
    @Provides
    fun provideGeoAlertDao(appDatabase: AppDatabase): GeoAlertDao =
        appDatabase.geoAlertDao()

    @Singleton
    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao =
        appDatabase.productDao()
}
