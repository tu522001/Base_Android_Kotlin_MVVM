package com.example.callapiinandroidkotlin.data.dataTransferObject.response

import com.example.callapiinandroidkotlin.data.dataTransferObject.model.Album
import com.example.callapiinandroidkotlin.data.dataTransferObject.model.Headers
import com.squareup.moshi.Json

data class ResponseAlbums (

    @Json(name = "headers")
    val headers: Headers,
    @Json(name = "results")
    val results: List<Album> = listOf()
)
