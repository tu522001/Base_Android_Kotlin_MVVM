package com.example.callapiinandroidkotlin.data.remote.service

import com.example.callapiinandroidkotlin.data.dataTransferObject.response.ResponseAlbums
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiAlbumsList {
    @GET("/v3.0/albums/tracks/")
    suspend fun fetchAlbumDetails(
        @Query("client_id") clientId: String,
        @Query("datebetween") dateBetween: String,
        @Query("offset") offset: String
    ): Response<ResponseAlbums>
}
