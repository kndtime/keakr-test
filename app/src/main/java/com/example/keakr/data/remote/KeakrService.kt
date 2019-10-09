package com.example.keakr.data.remote

import com.example.keakr.data.model.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KeakrService {
    @GET(RemoteContract.GET_BEAT)
    fun get_user_beats(
        @Path("userId") userdId: String,
        @Query("take") take: Int) : Observable<Response>

    @GET(RemoteContract.GET_PROFILE)
    fun get_user_profile(@Path("userId") userdId: String) : Observable<Response>
}