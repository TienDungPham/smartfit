package com.smartfit.smartfit

import android.app.Application
import android.content.Context
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smartfit.smartfit.di.AppComponent
import com.smartfit.smartfit.di.DaggerAppComponent

class SmartFitApplication : Application() {
    private var appComponent: AppComponent? = null
    fun appComponent(): AppComponent {
        synchronized(this) {
            if (appComponent == null) {
                appComponent = DaggerAppComponent
                    .builder()
                    .context(this)
                    .build()
            }
            return appComponent as AppComponent
        }
    }

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            enableStrictMode()
        }
        super.onCreate()
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }
}

fun AppCompatActivity.appComponent(context: Context): AppComponent {
    return (context.applicationContext as SmartFitApplication).appComponent()
}

fun Fragment.appComponent(context: Context): AppComponent {
    return (context.applicationContext as SmartFitApplication).appComponent()
}