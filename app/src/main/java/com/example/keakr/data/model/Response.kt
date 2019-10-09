package com.example.keakr.data.model

import com.squareup.moshi.Json

data class Response(
    @field:Json(name = "ContentType") val ContentType : String?,
    @field:Json(name = "SerializationSettings") val SerializationSettings : String?,
    @field:Json(name = "StatusCode") val StatusCode : Int,
    @field:Json(name = "Value") val Value : Value?
)

data class Value(
    @field:Json(name = "Code") val Code : Int?,
    @field:Json(name = "Content") val Content : String?,
    @field:Json(name = "Data") val Data : String?
)
