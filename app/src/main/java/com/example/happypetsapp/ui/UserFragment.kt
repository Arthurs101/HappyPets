package com.example.happypetsapp.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.happypetsapp.R
import com.example.happypetsapp.Services.Firebase.FirebaseSingleton
import com.example.happypetsapp.adapters.HomeAdapter
import com.example.happypetsapp.adapters.UserImagesAdapter
import com.example.happypetsapp.databinding.FragmentBreedinformationBinding
import com.example.happypetsapp.databinding.FragmentViewUserpageBinding
import com.example.happypetsapp.models.PublicationModel
import com.example.happypetsapp.models.privatePub
import com.example.happypetsapp.search.BreedInfoFragmentArgs
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import java.util.*

class UserFragment : Fragment() {
    private var _binding: FragmentViewUserpageBinding? = null
    private val binding get() = _binding!!
    val arg: BreedInfoFragmentArgs by navArgs()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserImagesAdapter
    private lateinit var manager: GridLayoutManager
    private val ref = FirebaseSingleton.RealTimeData.child("publications").child(FirebaseSingleton.Firebaseauth.currentUser?.displayName?.toString().toString())
    private var Pubs : MutableList<privatePub> = mutableListOf()
    private val GalleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent() , ActivityResultCallback {
        binding.ProfileImage.setImageURI(it)
        it?.let {
            val profileUpdates = userProfileChangeRequest {
                photoUri =  it
            }
            FirebaseSingleton.Firebaseauth.currentUser!!.updateProfile(profileUpdates)
        }
    })
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewUserpageBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textUsername.text = FirebaseSingleton.Firebaseauth.currentUser?.displayName?.toString().toString()
        manager = GridLayoutManager(requireContext(),3)
        adapter = UserImagesAdapter(Pubs)
        binding.Photos.layoutManager = manager
        binding.Photos.adapter = adapter




        ref.orderByKey().addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (postSnapshot in dataSnapshot.children) {
                    postSnapshot.getValue<privatePub>()?.let {
                        if(it.imageref.isNotBlank()){
                            Pubs.add(it)
                        }
                    }
                }
                Collections.reverse(Pubs)
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("Failed", "Coulnd't retrieve data")
            }
        })
        binding.Logout.setOnClickListener {
            FirebaseSingleton.Firebaseauth.signOut()
            val action =  UserFragmentDirections.actionNavigationUserToMainFragment()
            findNavController().navigate(action)
        }
        binding.ImageImporterUser.setOnClickListener {
            GalleryLauncher.launch("image/*")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}