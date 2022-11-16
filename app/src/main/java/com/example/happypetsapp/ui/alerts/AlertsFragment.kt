package com.example.happypetsapp.ui.alerts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.adapters.HomeAdapter
import com.example.happypetsapp.databinding.FragmentRecyclerMultiuseBinding
import com.example.happypetsapp.models.PublicationModel
import com.example.happypetsapp.ui.home.HomeViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class AlertsFragment: Fragment() {
    private var _binding: FragmentRecyclerMultiuseBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter: HomeAdapter
    private lateinit var manager: LinearLayoutManager
    private var Pubs : MutableList<PublicationModel> = mutableListOf()
    private val ref = FirebaseSingleton.RealTimeData.child("alerts").child("global")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val NotificationsViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentRecyclerMultiuseBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manager = LinearLayoutManager(requireContext())
        adapter = HomeAdapter(Pubs)
        binding.MainRecylcerView.layoutManager = manager
        binding.MainRecylcerView.adapter = adapter

        //detectar cualquier cambio en las publicaciones: Añadidos
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    postSnapshot.getValue<PublicationModel>()?.let {
                        Pubs.add(it)
                    }
                }
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