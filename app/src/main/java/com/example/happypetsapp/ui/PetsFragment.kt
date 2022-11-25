package com.example.happypetsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happypetsapp.R
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.adapters.HomeAdapter
import com.example.happypetsapp.adapters.PetsAdapter
import com.example.happypetsapp.databinding.FragmentFeedsBinding
import com.example.happypetsapp.databinding.FragmentRecyclerMultiuseBinding
import com.example.happypetsapp.models.PetModel
import com.example.happypetsapp.models.PublicationModel
import com.example.happypetsapp.ui.home.HomeViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import java.util.*

class PetsFragment: Fragment() {
    private var _binding: FragmentFeedsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!
    private var Pubs : MutableList<PetModel> = mutableListOf()
    private lateinit var adapter: PetsAdapter
    private lateinit var manager: LinearLayoutManager
    val ref = FirebaseSingleton.RealTimeData.child("mascots").child(FirebaseSingleton.Firebaseauth.currentUser?.displayName?.toString().toString())
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentFeedsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.PageTitle.text = "Mascotas"
        binding.newPublication.setImageResource(R.drawable.addpet)
        binding.newPublication.setOnClickListener {
            val action = PetsFragmentDirections.actionNavigationPetsToPetDisplay(true,PetModel())
            findNavController().navigate(action)
        }
        manager = LinearLayoutManager(requireContext())
        adapter = PetsAdapter(Pubs)
        binding.MainRecylcerView.layoutManager = manager
        binding.MainRecylcerView.adapter = adapter

        //detectar cualquier cambio en las publicaciones: Añadidos
        ref.orderByKey().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    postSnapshot.getValue<PetModel>()?.let {
                        Pubs.add(it)
                    }
                }
                Collections.reverse(Pubs)
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w( "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}