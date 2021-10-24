package com.tobiasstrom.stairs

import android.app.Application
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.tobiasstrom.stairs.common.commonModule
import com.tobiasstrom.stairs.home.homeModule
import com.tobiasstrom.stairs.main.mainModule
import com.tobiasstrom.stairs.settings.settingsModule
import com.tobiasstrom.stairs.startup.startupModule
import com.tobiasstrom.stairs.stats.statsModule
import com.tobiasstrom.stairs.tracking.trackingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    mainModule, commonModule, startupModule, homeModule, settingsModule, statsModule, trackingModule
                )
            )
        }
        initLogging()
    }

    private fun initLogging() {
        val min = if (BuildConfig.DEBUG) Log.VERBOSE else Log.DEBUG
        Timber.plant(if (BuildConfig.DEBUG) Timber.DebugTree() else CrashReportingTree(min))
    }

    private class CrashReportingTree(private val minPriority: Int) : Timber.Tree() {
        override fun isLoggable(tag: String?, priority: Int): Boolean = priority > minPriority
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            super.log(priority, tag, message, t)
            FirebaseCrashlytics.getInstance().let { crashlytics ->
                if (t != null) {
                    crashlytics.recordException(t)
                } else {
                    crashlytics.log("$message [priority=$priority, tag=$tag]")
                }
            }
        }
    }
}