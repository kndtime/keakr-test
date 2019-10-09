package com.example.keakr.data.model

import com.squareup.moshi.Json

data class User(
    @field:Json(name = "id") val id : String?,
    @field:Json(name = "username") val username : String?,
    @field:Json(name = "pictureUrl") val pictureUrl : String?,
    @field:Json(name = "smallPictureUrl") val smallPictureUrl : String?,
    @field:Json(name = "biography") val biography : String?,
    @field:Json(name = "stats") val stats: Stats?,
    @field:Json(name = "dominantColor") val dominantColor : String?
)

data class Stats(
    @field:Json(name = "beatCount") val beatCount : Int?,
    @field:Json(name = "listeningCount") val listeningCount : Int?,
    @field:Json(name = "shareCount") val shareCount : Int?,
    @field:Json(name = "viewCount") val viewCount : Int?
)

data class Beat(
    @field:Json(name = "posterUrl") val posterUrl : String?,
    @field:Json(name = "title") val title : String?,
    @field:Json(name = "artist") val artist : String?,
    @field:Json(name = "stats") val stats: Stats?,
    @field:Json(name = "genres") val genres : List<String>?,
    @field:Json(name = "author") val author : User,
    @field:Json(name = "pictureUrl") val pictureUrl: String?,
    @field:Json(name = "dominantColor") val dominantColor: String?
)