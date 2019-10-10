package com.example.keakr.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Response(
    @field:Json(name = "ContentType") val ContentType : String?,
    @field:Json(name = "SerializationSettings") val SerializationSettings : String?,
    @field:Json(name = "StatusCode") val StatusCode : Int,
    @field:Json(name = "Value") val Value : Value?,
    @field:Json(name = "user") val user: User?,
    @field:Json(name = "latestContent") val latestContent : Content,
    @field:Json(name = "items") val items: List<Beat>
) : Parcelable

@Parcelize
data class Value(
    @field:Json(name = "Code") val Code : Int?,
    @field:Json(name = "Content") val Content : String?,
    @field:Json(name = "Data") val Data : String?
) : Parcelable

@Parcelize
data class Content(
    @field:Json(name = "items") val items : List<Beat>
) : Parcelable
