package com.example.callapiinandroidkotlin.ui.component.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.callapiinandroidkotlin.data.dataTransferObject.model.Album
import com.example.callapiinandroidkotlin.data.dataTransferObject.model.Track
import com.example.callapiinandroidkotlin.databinding.ItemAlbumBinding

class AlbumAdapter(var listAlbum : MutableList<Album>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumAdapter.AlbumViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        var itemBinding = ItemAlbumBinding.inflate(layoutInflater, parent, false)
        return AlbumViewHolder(itemBinding)
    }

    inner class AlbumViewHolder(var itemAlbumBinding: ItemAlbumBinding) :RecyclerView.ViewHolder(itemAlbumBinding.root) {
        fun bind(album: Album) {
            Glide.with(itemAlbumBinding.root).load(album.image).into(itemAlbumBinding.imgOtherJobs)
            itemAlbumBinding.tvTitle.text = album.name
        }
    }

    override fun onBindViewHolder(holder: AlbumAdapter.AlbumViewHolder, position: Int) {
        holder.bind(listAlbum[position])
    }

    override fun getItemCount(): Int {
        return listAlbum.size
    }
}