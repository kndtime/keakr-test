package com.example.keakr.di

import com.example.keakr.ui.common.BaseViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, KeakrModule::class])
@Singleton
interface AppComponent {

    fun inject(baseViewModel: BaseViewModel)
}