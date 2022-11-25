package com.example.happypetsapp.adapters

import android.view.Display
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.R
import com.example.happypetsapp.Services.Api.information.Animal
import com.example.happypetsapp.databinding.ItemSearchBinding
import com.example.happypetsapp.search.SearchFragmentDirections
import com.example.happypetsapp.search.SearchViewModel
import com.squareup.picasso.Picasso

class SearchAdapter( private val ModelView: SearchViewModel) : RecyclerView.Adapter<SearchAdapter.SearchHolder>() {
    private var Animals: List<Animal> = listOf()
    inner class SearchHolder(val binding: ItemSearchBinding) : RecyclerView.ViewHolder(binding.root){
    var photoref = binding.breedphoto
    var name = binding.breedname
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
       val binding = ItemSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
       return SearchHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val item = Animals.get(position)
        ModelView.searchImage(item.name)
        holder.name.text = item.name
        val ref = ModelView.searchImage(item.name)
        if(ref.isNotBlank()) {
            Picasso.get().load(ref).error(R.drawable.notfound).into(holder.photoref)
            item.ref = ref
        }else{
            holder.photoref.setImageResource(R.drawable.notfound)
        }
        holder.itemView.setOnClickListener{
            val action = SearchFragmentDirections.actionNavigationBuscarToBreedInfoFragment(item)
            holder.binding.root.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return Animals.size
    }
    fun updatedata(list: List<Animal>){
        this.Animals = list
        notifyDataSetChanged();
    }

}