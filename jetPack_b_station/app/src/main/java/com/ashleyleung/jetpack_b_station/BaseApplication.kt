package com.ashleyleung.jetpack_b_station

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import timber.log.Timber

/**
 * @author lxy
 * @name
 * @class describe
 * @date 2022-06-15
 *
 */
abstract class BaseApplication : Application() {
    private class CrashReportTree : Timber.Tree() {
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {}
    }
    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate(){
        super.onCreate()
        Log.e("BaseApplication", "onCreate")
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportTree())
        }
        Timber.e("Timber init")
    }

    val applicationScope = CoroutineScope(Dispatchers.Default)

}