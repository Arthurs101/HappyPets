package com.example.happypetsapp.adapters


import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.databinding.ItemPublicationLayoutBinding
import com.example.happypetsapp.models.PublicationModel

class HomeAdapter (private val publicationsList: List<PublicationModel>): RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    inner class  HomeHolder(val binding: ItemPublicationLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        val userphoto = binding.userProfilePicture
        val publicationphoto = binding.imageView2
        val username = binding.Username
        val description = binding.Description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}