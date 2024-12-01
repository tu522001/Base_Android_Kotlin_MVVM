package com.example.callapiinandroidkotlin.ui.component.main.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.callapiinandroidkotlin.data.Resource
import com.example.callapiinandroidkotlin.data.repository.DataRepositorySource
import com.example.callapiinandroidkotlin.data.dataTransferObject.response.ResponseAlbums
import com.example.callapiinandroidkotlin.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataRepository: DataRepositorySource) :
    BaseViewModel() {

    private val _albumResponse  = MutableLiveData<Resource<ResponseAlbums>>()
    val albumResponse: LiveData<Resource<ResponseAlbums>> get() = _albumResponse

    fun loadAlbums(clientId : String, dateBetween : String, offset: String) {
        viewModelScope.launch {
            _albumResponse .value = Resource.Loading
            try {
                dataRepository.requestAlbum(clientId,dateBetween,offset).collect { result ->
                    _albumResponse .value = result
                    Log.d("OOO","Thanh cong")
                }
            } catch (e: Exception) {
                // Handle the exception
                _albumResponse .value = Resource.Error(null, e.message)
                Log.d("OOO","That Bai")
            }
        }
    }
}
