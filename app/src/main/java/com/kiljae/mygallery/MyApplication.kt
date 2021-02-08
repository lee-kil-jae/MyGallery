package com.kiljae.mygallery

import androidx.multidex.MultiDexApplication
import com.kiljae.mygallery.common.myDiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : MultiDexApplication() {

    companion object{
        val TAG = "MyApplication"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(myDiModule)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }

}