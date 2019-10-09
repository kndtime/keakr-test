package com.example.keakr.di

import android.app.Application

class KeakrApp : Application() {
    companion object {
        lateinit var instance : KeakrApp
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        instance = this
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .keakrModule(KeakrModule()).build()
    }
}