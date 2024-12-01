package com.example.callapiinandroidkotlin

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Prefs.initPrefs(this)
        Hawk.init(this).build()
    }
}
