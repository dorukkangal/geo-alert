package com.dorukkangal.geoalert.di.module

import android.content.Context
import com.dorukkangal.geoalert.util.DialogHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object ActivityModule {

    @ActivityScoped
    @Provides
    fun dialogHelper(@ActivityContext context: Context): DialogHelper = DialogHelper(context)
}
