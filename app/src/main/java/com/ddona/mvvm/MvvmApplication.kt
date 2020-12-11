package com.ddona.mvvm

import android.app.Application
import com.ddona.mvvm.util.AppDebugTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MvvmApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(AppDebugTree())
        }
    }
}