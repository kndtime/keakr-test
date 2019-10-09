package com.example.keakr.data.repository

import com.example.keakr.di.KeakrModule
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val keakrModule: KeakrModule) : RepositoryInterface{

}