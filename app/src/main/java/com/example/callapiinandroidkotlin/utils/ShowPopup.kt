package com.example.callapiinandroidkotlin.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.callapiinandroidkotlin.ui.base.BasePopup

class ShowPopup(private val context : Context) {
    fun showErrorPopup(errorMessage: String) {
        val basePopup = BasePopup()
        basePopup.showPopup((context as AppCompatActivity).supportFragmentManager, errorMessage)
    }
}