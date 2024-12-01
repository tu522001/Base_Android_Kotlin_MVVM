package com.example.callapiinandroidkotlin.data.dataTransferObject.model

data class Track(
    val id: String,
    val position: String,
    val name: String,
    val duration: String,
    val licenseCcurl: String,
    val audio: String,
    val audioDownload: String,
    val audioDownloadAllowed: Boolean
)