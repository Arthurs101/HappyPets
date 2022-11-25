package com.example.happypetsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.R
import com.example.happypetsapp.databinding.ItemSearchBinding
import com.example.happypetsapp.models.PetModel
import com.example.happypetsapp.ui.PetsFragmentDirections
import com.squareup.picasso.Picasso


class PetsAdapter(private val Pets: List<PetModel>): RecyclerView.Adapter<PetsAdapter.PHolder>() {
    inner class PHolder(val binding: ItemSearchBinding): RecyclerView.ViewHolder(binding.root){
        var photo = binding.breedphoto
        var name = binding.breedname
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetsAdapter.PHolder {
        val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PHolder(binding)
    }

    override fun onBindViewHolder(holder: PHolder, position: Int) {
        val item = Pets.get(position)
        holder.name.text = item.name
        if(item.imgref.isNotEmpty()){
            Picasso.get().load(item.imgref).error(R.drawable.notfound).placeholder(R.drawable.addpet)
                .into(holder.photo)
        }
        holder.itemView.setOnClickListener{
            val action = PetsFragmentDirections.actionNavigationPetsToPetDisplay(false,item)
            holder.binding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return Pets.size
    }


}