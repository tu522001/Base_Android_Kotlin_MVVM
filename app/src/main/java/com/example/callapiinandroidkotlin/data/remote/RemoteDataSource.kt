package com.example.callapiinandroidkotlin.data.remote

import com.example.callapiinandroidkotlin.data.Resource
import com.example.callapiinandroidkotlin.data.dataTransferObject.response.ResponseAlbums
import retrofit2.http.Query

internal interface RemoteDataSource {
    suspend fun requestDataAlbum(clientId: String, dateBetween: String, offset: String
    ): Resource<ResponseAlbums>
}

