package com.example.callapiinandroidkotlin.data.dataTransferObject.model

data class Headers(
    val status: String,
    val code: Int,
    val errorMessage: String,
    val warnings: String,
    val resultsCount: Int,
    val next : String
)