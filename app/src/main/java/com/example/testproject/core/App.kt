package com.example.testproject.core

import android.app.Application
import com.example.testproject.di.DaggerAppComponent

class App : Application() {

    val component by lazy {
        DaggerAppComponent.factory()
            .create(this)
    }
}