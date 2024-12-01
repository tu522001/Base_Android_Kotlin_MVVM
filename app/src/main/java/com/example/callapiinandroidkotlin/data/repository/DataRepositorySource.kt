package com.example.callapiinandroidkotlin.data.repository

import com.example.callapiinandroidkotlin.data.Resource
import com.example.callapiinandroidkotlin.data.dataTransferObject.response.ResponseAlbums
import kotlinx.coroutines.flow.Flow

interface DataRepositorySource {
    suspend fun requestAlbum(clientId: String, dateBetween: String, offset: String): Flow<Resource<ResponseAlbums>>
}
