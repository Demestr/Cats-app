package com.example.catsapp

import android.app.Application
import com.example.catsapp.di.AppComponent
import com.example.catsapp.di.DaggerAppComponent

class CatApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {

        return DaggerAppComponent.factory().create(applicationContext)
    }
}