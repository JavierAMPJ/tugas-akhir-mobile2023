package com.d121211006.culina

import android.app.Application
import com.d121211006.culina.data.AppContainer
import com.d121211006.culina.data.DefaultAppContainer

class MyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer()
    }
}