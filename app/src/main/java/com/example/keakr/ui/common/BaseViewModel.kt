package com.example.keakr.ui.common

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import com.example.keakr.data.repository.Repository
import com.example.keakr.di.KeakrApp
import javax.inject.Inject

open class BaseViewModel : ViewModel(), LifecycleObserver {
    @Inject
    lateinit var symplRepository: Repository
    /*@Inject
    lateinit var accountManager: AccountManager*/

    init {
        initializeDagger()
    }

    private fun initializeDagger() = KeakrApp.appComponent.inject(this)
}