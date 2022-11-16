package com.example.happypetsapp.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.R
import com.example.happypetsapp.databinding.ItemPublicationLayoutBinding
import com.example.happypetsapp.databinding.ItemSearchBinding
import com.example.happypetsapp.models.PublicationModel
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.squareup.picasso.Picasso

class HomeAdapter (private val Publications: List<PublicationModel>): RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    inner class HomeHolder(val binding: ItemPublicationLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val userphoto = binding.userProfilePicture
        val publicationphoto = binding.imageView2
        val username = binding.Username
        val description = binding.Description
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        val binding =
            ItemPublicationLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        Publications.get(position).userImgRef?.let {
            Picasso.get().load(it).placeholder(
                R.drawable.happy
            ).error(R.drawable.notfound).into(holder.userphoto)

            Publications.get(position).publicationImageRef?.let {
                if (it.isNotBlank()){
                    Picasso.get().load(it).placeholder(
                        R.drawable.happy
                    ).error(R.drawable.notfound).into(holder.publicationphoto)
                }else{
                    holder.publicationphoto.visibility = View.GONE
                }

            }
            Publications.get(position).username?.let {
                holder.username.text = it
            }
            Publications.get(position).description?.let {
                holder.description.text = it
            }
        }
    }

    override fun getItemCount(): Int {
       return Publications.size
    }


}