package com.example.callapiinandroidkotlin.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {
     var _binding: T? = null
     val binding: T
        get() = _binding as T



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = getViewBinding()

        initViewModel()
        initView()
        addEvent()
        addObservers()
        initData()
    }



    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    abstract fun getViewBinding(): T

    open fun initViewModel() = Unit

    open fun initView() = Unit

    open fun addEvent() = Unit

    open fun addObservers() = Unit

    open fun initData() = Unit

}
