package com.example.callapiinandroidkotlin.data.repository

import com.example.callapiinandroidkotlin.data.Resource
import com.example.callapiinandroidkotlin.data.remote.RemoteData
import com.example.callapiinandroidkotlin.data.dataTransferObject.response.ResponseAlbums
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DataRepository @Inject constructor(
    private val remoteRepository: RemoteData,
    private val ioDispatcher: CoroutineContext
) :
    DataRepositorySource {

    override suspend fun requestAlbum(clientId: String, dateBetween: String, offset: String) : Flow<Resource<ResponseAlbums>>{
        return flow {
            emit(remoteRepository.requestDataAlbum(clientId,dateBetween,offset))
        }.flowOn(ioDispatcher)
    }


}