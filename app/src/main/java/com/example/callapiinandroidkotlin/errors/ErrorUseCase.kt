package com.example.callapiinandroidkotlin.errors

import com.example.callapiinandroidkotlin.data.error.Error


interface ErrorUseCase {
    fun getError(errorCode: Int): Error
}
