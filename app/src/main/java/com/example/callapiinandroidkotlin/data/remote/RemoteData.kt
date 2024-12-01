package com.example.callapiinandroidkotlin.data.remote

import com.example.callapiinandroidkotlin.errors.ErrorManager
import com.example.callapiinandroidkotlin.data.Resource
import com.example.callapiinandroidkotlin.data.error.NETWORK_ERROR
import com.example.callapiinandroidkotlin.data.error.NO_INTERNET_CONNECTION
import com.example.callapiinandroidkotlin.data.dataTransferObject.response.ResponseAlbums
import com.example.callapiinandroidkotlin.data.remote.service.ApiAlbumsList
import com.example.callapiinandroidkotlin.utils.NetWorkConnectivity
import retrofit2.Response
import retrofit2.http.Query

import javax.inject.Inject

class RemoteData @Inject constructor(
    private val serviceGenerator: ServiceGenerator,
    private val networkConnectivity: NetWorkConnectivity,
    private val errorManager: ErrorManager
) : RemoteDataSource {

    private suspend fun processCall(responseCall: suspend () -> Response<*>): Any? {
        if (!networkConnectivity.isConnected()) {
            return NO_INTERNET_CONNECTION
        }
        return try {
            val response = responseCall.invoke()
            val responseCode = response.code()
            if (response.isSuccessful) {
                response.body()
            } else {
                responseCode
            }
        } catch (e: Exception) {
            NETWORK_ERROR
        }
    }

    override suspend fun requestDataAlbum(clientId: String, dateBetween: String, offset: String
    ): Resource<ResponseAlbums> {
        val framesServices = serviceGenerator.createService(ApiAlbumsList::class.java)
        return when (val response = processCall {
            framesServices.fetchAlbumDetails(clientId, dateBetween,offset)
        }) {
            is ResponseAlbums -> {
                Resource.Success(data = response as ResponseAlbums)
            }

            else -> {
                Resource.Error(
                    errorCode = response as Int,
                    message = errorManager.getError(response).description
                )
            }

        }
    }
}