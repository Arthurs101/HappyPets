package com.example.happypetsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.R
import com.example.happypetsapp.databinding.ItemPhotoBinding
import com.example.happypetsapp.databinding.ItemPublicationLayoutBinding
import com.example.happypetsapp.models.PublicationModel
import com.example.happypetsapp.models.privatePub
import com.squareup.picasso.Picasso

class UserImagesAdapter(private var Images: List<privatePub>) : RecyclerView.Adapter<UserImagesAdapter.ImgHolder>() {
    inner class ImgHolder(val binding: ItemPhotoBinding) : RecyclerView.ViewHolder(binding.root) {
        val photo = binding.Photo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImgHolder(binding)
    }

    override fun onBindViewHolder(holder: ImgHolder, position: Int) {
        Images.get(position).imageref?.let {
            if (it.isNotEmpty()) {
                Picasso.get().load(it).placeholder(
                    R.drawable.happy
                ).error(R.drawable.notfound).into(holder.photo)
            }
        }
    }

    override fun getItemCount(): Int {
        return Images.size
    }
}