package com.example.kattabozortest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import utils.LiveEvent

@HiltAndroidApp
class KattaBozorApplication : Application() {

    companion object {
        @JvmStatic
        val fcmMessages: LiveEvent<Any> = LiveEvent()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())
        }
    }
}