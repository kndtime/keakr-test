package com.example.keakr.data.repository

import androidx.lifecycle.LiveData
import com.example.keakr.data.model.Response

interface RepositoryInterface {

    fun get_user_profile(userId: String, take: Int): LiveData<Response>
    fun get_user_beats(userdId: String, take: Int) : LiveData<Response>
}