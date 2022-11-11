package com.example.happypetsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import com.example.happypetsapp.databinding.ItemUbicationBinding

class BreedInformationAdapter(private val Ubications: List<String>) : RecyclerView.Adapter<BreedInformationAdapter.UbicationsHolder>()  {
    inner class  UbicationsHolder(val binding: ItemUbicationBinding) : RecyclerView.ViewHolder(binding.root){
        val name = binding.Content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UbicationsHolder {
        val binding = ItemUbicationBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UbicationsHolder(binding)
    }

    override fun onBindViewHolder(holder: UbicationsHolder, position: Int) {
        val text = Ubications.get(position)
        holder.name.text = text
    }

    override fun getItemCount(): Int {
       return Ubications.size
    }


}