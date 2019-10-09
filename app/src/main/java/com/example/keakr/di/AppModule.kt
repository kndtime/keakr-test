package com.example.keakr.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val kA : KeakrApp){
    @Provides
    @Singleton
    fun provideContext(): Context = kA
}