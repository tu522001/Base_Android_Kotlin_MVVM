package com.example.callapiinandroidkotlin.ui.component.main.view

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.callapiinandroidkotlin.data.Resource
import com.example.callapiinandroidkotlin.data.dataTransferObject.model.Album
import com.example.callapiinandroidkotlin.ui.base.BaseActivity
import com.example.callapiinandroidkotlin.databinding.ActivityMainBinding
import com.example.callapiinandroidkotlin.data.dataTransferObject.response.ResponseAlbums
import com.example.callapiinandroidkotlin.ui.component.main.viewmodel.HomeViewModel
import com.example.callapiinandroidkotlin.utils.ShowPopup
import com.example.callapiinandroidkotlin.utils.observe
import kotlinx.coroutines.launch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val showPopup = ShowPopup(this@MainActivity)
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        super.initView()
        setContentView(binding.root)
    }
    override fun initData() {
        super.initData()
        homeViewModel.loadAlbums("995bc5de","2021-01-01_2022-01-01","20")
    }
    override fun addObservers() {
        observe(homeViewModel.albumResponse, ::handleAlbumListResponse)
    }
    private fun handleAlbumListResponse(resource: Resource<ResponseAlbums>) {
        lifecycleScope.launch {

            resource.whenSuccess {
                if (resource.data != null) handleUIAlbumList(resource.data.results)
            }.whenLoading {
                //showIconLoading()
            }.whenError {
                showPopup.showErrorPopup(it.message.toString())
            }
        }
    }

    private fun handleUIAlbumList(results: List<Album>) {
        Log.d("DATASS", results.toString())
    }
}