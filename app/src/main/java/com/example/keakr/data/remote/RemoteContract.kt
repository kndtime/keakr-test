package com.example.keakr.data.remote

class RemoteContract {

companion object {
    const val USER_ID = 11237314400

    //URL
    const val GET_PROFILE = "/v4/users/{userId}/profile"
    const val GET_BEAT = "/v1/beats/users/{userId}"
}
}