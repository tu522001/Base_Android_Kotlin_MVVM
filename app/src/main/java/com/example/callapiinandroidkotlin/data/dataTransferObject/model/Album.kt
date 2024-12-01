package com.example.callapiinandroidkotlin.data.dataTransferObject.model

data class Album(
    val id: String,
    val name: String,
    val releaseDate: String,
    val artistId: String,
    val artistName: String,
    val image: String,
    val zip: String,
    val zipAllowed: Boolean,
    val tracks: List<Track>
)