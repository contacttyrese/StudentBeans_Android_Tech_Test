package com.example.sbtechincaltest.model

import com.squareup.moshi.Json

data class Photo(
    @field:Json(name = "albumId") val albumId: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "url") val url: String,
    @field:Json(name = "thumbnailUrl") val thumbnailUrl: String
    )